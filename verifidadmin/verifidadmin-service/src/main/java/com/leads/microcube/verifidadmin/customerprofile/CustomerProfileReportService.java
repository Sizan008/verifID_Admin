package com.leads.microcube.verifidadmin.customerprofile;

import com.fasterxml.jackson.databind.JsonNode;
import com.leads.microcube.verifidadmin.customerprofile.client.CustomerProfileReportGateway;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteDocumentFile;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteDocumentResponse;
import com.leads.microcube.verifidadmin.customerprofile.client.dto.RemoteStatusResponse;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerReportResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToFoConverter;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerProfileReportService {

  private final CustomerProfileReportGateway reportGateway;

  public CustomerReportResponse generate(Long trackingNo) {
    RemoteStatusResponse report = reportGateway.retrieveReport(trackingNo);
    if (report == null || !"SUCCESS".equalsIgnoreCase(report.getStatus())
        || report.getResult() == null || report.getResult().isNull()) {
      String message = report == null ? "No response" : report.getMessage();
      return CustomerReportResponse.builder()
          .success(false)
          .errorMessage("Report API Error: " + nullSafe(message))
          .build();
    }

    String encodedPdf = retrieveBuffer(report.getResult());
    if (!StringUtils.hasText(encodedPdf)) {
      return CustomerReportResponse.builder()
          .success(false)
          .errorMessage("API returned empty PDF data.")
          .build();
    }

    byte[] mainPdf;
    try {
      mainPdf = Base64.getDecoder().decode(encodedPdf);
    } catch (IllegalArgumentException exception) {
      return CustomerReportResponse.builder()
          .success(false)
          .errorMessage("API returned invalid base64 PDF.")
          .build();
    }

    try {
      List<byte[]> supportingDocuments = retrieveSupportingDocuments(trackingNo);
      byte[] merged = merge(mainPdf, supportingDocuments);
      return CustomerReportResponse.builder()
          .success(true)
          .pdfData(Base64.getEncoder().encodeToString(merged))
          .build();
    } catch (IOException exception) {
      log.error("Unable to merge customer report. TrackingNo={}", trackingNo, exception);
      return CustomerReportResponse.builder()
          .success(false)
          .result("Exception occurred : " + exception.getMessage())
          .build();
    }
  }

  private List<byte[]> retrieveSupportingDocuments(Long trackingNo) {
    RemoteDocumentResponse response = reportGateway.retrieveDocuments(trackingNo);
    if (response == null || !"SUCCESS".equalsIgnoreCase(response.getStatus())
        || response.getFiles() == null || response.getFiles().isEmpty()) {
      return List.of();
    }

    List<byte[]> documents = new ArrayList<>();
    for (RemoteDocumentFile file : response.getFiles()) {
      try {
        byte[] converted = convert(file);
        if (converted != null && converted.length > 0) {
          documents.add(converted);
        }
      } catch (RuntimeException | IOException exception) {
        log.warn("Unable to convert supporting document {}.", file.getFileName(), exception);
      }
    }
    return documents;
  }

  private byte[] convert(RemoteDocumentFile file) throws IOException {
    if (file == null || !StringUtils.hasText(file.getBase64())) {
      return null;
    }
    byte[] content = Base64.getDecoder().decode(file.getBase64());
    String extension = normalizeExtension(file.getExtension(), file.getFileName());
    if ("pdf".equals(extension)) {
      return content;
    }
    if (List.of("jpg", "jpeg", "png", "bmp", "gif", "tif", "tiff").contains(extension)) {
      return imageToPdf(content);
    }
    if ("doc".equals(extension) || "docx".equals(extension)) {
      return wordToPdf(content, extension);
    }
    return null;
  }

  private byte[] wordToPdf(byte[] content, String extension) throws IOException {
    if ("docx".equals(extension)) {
      return docxToPdf(content);
    }
    return docToPdf(content);
  }

  private byte[] docxToPdf(byte[] content) throws IOException {
    WordprocessingMLPackage wordPackage = null;
    try (ByteArrayInputStream input = new ByteArrayInputStream(content);
        ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      wordPackage = WordprocessingMLPackage.load(input);
      FOSettings settings = Docx4J.createFOSettings();
      settings.setWmlPackage(wordPackage);
      Docx4J.toFO(settings, output, Docx4J.FLAG_EXPORT_PREFER_XSL);
      return output.toByteArray();
    } catch (Docx4JException exception) {
      throw new IOException("Unable to convert DOCX document to PDF.", exception);
    } finally {
      deleteEmbeddedFontTempFiles(wordPackage);
    }
  }

  private byte[] docToPdf(byte[] content) throws IOException {
    try (ByteArrayInputStream input = new ByteArrayInputStream(content);
        HWPFDocument wordDocument = new HWPFDocument(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware(true);
      WordToFoConverter converter =
          new WordToFoConverter(documentBuilderFactory.newDocumentBuilder().newDocument());
      converter.processDocument(wordDocument);

      FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
      Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, output);
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.transform(
          new DOMSource(converter.getDocument()),
          new SAXResult(fop.getDefaultHandler()));
      return output.toByteArray();
    } catch (FOPException | ParserConfigurationException | TransformerException exception) {
      throw new IOException("Unable to convert DOC document to PDF.", exception);
    }
  }

  private void deleteEmbeddedFontTempFiles(WordprocessingMLPackage wordPackage) {
    if (wordPackage == null || wordPackage.getMainDocumentPart() == null
        || wordPackage.getMainDocumentPart().getFontTablePart() == null) {
      return;
    }
    try {
      wordPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
    } catch (RuntimeException exception) {
      log.warn("Unable to clean up embedded DOCX font files.", exception);
    }
  }

  private byte[] imageToPdf(byte[] content) throws IOException {
    BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
    if (image == null) {
      return null;
    }
    try (PDDocument document = new PDDocument();
        ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      PDPage page = new PDPage(PDRectangle.A4);
      document.addPage(page);
      PDImageXObject pdfImage = LosslessFactory.createFromImage(document, image);
      float maxWidth = page.getMediaBox().getWidth() - 40;
      float maxHeight = page.getMediaBox().getHeight() - 40;
      float scale = Math.min(maxWidth / pdfImage.getWidth(), maxHeight / pdfImage.getHeight());
      float width = pdfImage.getWidth() * scale;
      float height = pdfImage.getHeight() * scale;
      float x = (page.getMediaBox().getWidth() - width) / 2;
      float y = (page.getMediaBox().getHeight() - height) / 2;
      try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
        stream.drawImage(pdfImage, x, y, width, height);
      }
      document.save(output);
      return output.toByteArray();
    }
  }

  private byte[] createHeaderPage() throws IOException {
    try (PDDocument document = new PDDocument();
        ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      PDPage page = new PDPage(PDRectangle.A4);
      document.addPage(page);
      try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
        stream.setNonStrokingColor(0, 84, 166);
        stream.addRect(0, page.getMediaBox().getHeight() - 120, page.getMediaBox().getWidth(), 120);
        stream.fill();
        stream.beginText();
        stream.setNonStrokingColor(255, 255, 255);
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 28);
        stream.newLineAtOffset(155, page.getMediaBox().getHeight() - 75);
        stream.showText("Additional Documents");
        stream.endText();
        stream.beginText();
        stream.setNonStrokingColor(80, 80, 80);
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 13);
        stream.newLineAtOffset(92, page.getMediaBox().getHeight() - 205);
        stream.showText(
            "The following pages contain supporting documents submitted with this application.");
        stream.endText();
      }
      document.save(output);
      return output.toByteArray();
    }
  }

  private byte[] merge(byte[] mainPdf, List<byte[]> supportingDocuments) throws IOException {
    if (supportingDocuments == null || supportingDocuments.isEmpty()) {
      return mainPdf;
    }
    PDFMergerUtility merger = new PDFMergerUtility();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    merger.setDestinationStream(output);
    merger.addSource(new RandomAccessReadBuffer(new ByteArrayInputStream(mainPdf)));
    merger.addSource(new RandomAccessReadBuffer(new ByteArrayInputStream(createHeaderPage())));
    for (byte[] document : supportingDocuments) {
      merger.addSource(new RandomAccessReadBuffer(new ByteArrayInputStream(document)));
    }
    merger.mergeDocuments(IOUtils.createMemoryOnlyStreamCache());
    return output.toByteArray();
  }

  private String retrieveBuffer(JsonNode result) {
    JsonNode buffer = result.get("_buffer");
    if (buffer == null && result.isTextual()) {
      return result.asText();
    }
    return buffer == null || buffer.isNull() ? null : buffer.asText();
  }

  private String normalizeExtension(String extension, String fileName) {
    String value = extension;
    if (!StringUtils.hasText(value) && StringUtils.hasText(fileName) && fileName.contains(".")) {
      value = fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    if (!StringUtils.hasText(value)) {
      return "";
    }
    value = value.trim().toLowerCase();
    return value.startsWith(".") ? value.substring(1) : value;
  }

  private String nullSafe(String value) {
    return value == null ? "" : value;
  }
}
