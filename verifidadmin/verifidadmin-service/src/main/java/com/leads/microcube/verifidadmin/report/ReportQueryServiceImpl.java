package com.leads.microcube.verifidadmin.report;

import com.leads.microcube.verifidadmin.customerprofile.CustomerProfileQueryService;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotos;
import com.leads.microcube.verifidadmin.customerprofile.query.CustomerPhotosResponse;
import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhoto;
import com.leads.microcube.verifidadmin.report.query.MergedCustomerPhotoResponse;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportQueryServiceImpl implements ReportQueryService {

  private static final int CUSTOMER_PHOTO_HEIGHT = 768;
  private static final int SIGNATURE_WIDTH = 1024;
  private static final int SIGNATURE_HEIGHT = 768;
  private static final String IMAGE_DATA_PREFIX = "base64,";
  private static final String SEPARATOR_IMAGE =
      "iVBORw0KGgoAAAANSUhEUgAAABAAAAFWCAYAAABghR2kAAAAAXNSR0IArs4c6QAAAARnQU1BAACx"
          + "jwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAB7SURBVHhe7cwxEQAwCACxKsG/S3qvgTVD1ryZ"
          + "2QuBIAJBBIIIBBEIIhBEIIhAEIEgAkEEgggEEQgiEEQgiEAQgSACQQSCCAQRCCIQRCCIQBCBIAJBB"
          + "IIIBBEIIhBEIIhAEIEgAkEEgggEEQgiEEQgiEAQgSACQQTnYPYD1hXQBQImtF0AAAAASUVORK5CYII=";

  private final CustomerProfileQueryService customerProfileQueryService;

  @Override
  public MergedCustomerPhotoResponse retrieveMergedCustomerPhoto(MergedCustomerPhoto query) {
    try {
      Long trackingNo = parseTrackingNo(query);
      CustomerPhotosResponse photos =
          customerProfileQueryService.retrievePhotos(new CustomerPhotos(trackingNo));

      BufferedImage customerPhoto = decodeImage(photos.getFromUploaded());
      BufferedImage resizedCustomer =
          resizeImage(customerPhoto, customerPhoto.getWidth(), CUSTOMER_PHOTO_HEIGHT);
      BufferedImage separator = decodeImage(SEPARATOR_IMAGE);
      BufferedImage signature = decodeImage(photos.getFromSignature());
      BufferedImage resizedSignature =
          resizeImage(signature, SIGNATURE_WIDTH, SIGNATURE_HEIGHT);
      BufferedImage mergedImage =
          mergeImages(List.of(resizedCustomer, separator, resizedSignature));

      return MergedCustomerPhotoResponse.builder()
          .pdfData(encodeJpeg(mergedImage))
          .build();
    } catch (RuntimeException | IOException exception) {
      log.warn("Unable to merge customer photo and signature.", exception);
      return MergedCustomerPhotoResponse.builder()
          .result("Exception occurred : " + safeMessage(exception))
          .build();
    }
  }

  private Long parseTrackingNo(MergedCustomerPhoto query) {
    if (query == null || !StringUtils.hasText(query.getTrackingNo())) {
      throw new IllegalArgumentException("trackingNo is required.");
    }
    try {
      return Long.valueOf(query.getTrackingNo().trim());
    } catch (NumberFormatException exception) {
      throw new IllegalArgumentException("trackingNo must be numeric.", exception);
    }
  }

  private BufferedImage decodeImage(String encodedImage) throws IOException {
    if (!StringUtils.hasText(encodedImage)) {
      throw new IOException("Image data is missing.");
    }
    String value = encodedImage.trim();
    int prefixIndex = value.indexOf(IMAGE_DATA_PREFIX);
    if (prefixIndex >= 0) {
      value = value.substring(prefixIndex + IMAGE_DATA_PREFIX.length());
    }
    byte[] imageBytes = Base64.getDecoder().decode(value);
    BufferedImage image = javax.imageio.ImageIO.read(new ByteArrayInputStream(imageBytes));
    if (image == null) {
      throw new IOException("Image data is invalid.");
    }
    return image;
  }

  private BufferedImage resizeImage(BufferedImage source, int width, int height) {
    BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = resized.createGraphics();
    try {
      graphics.setRenderingHint(
          RenderingHints.KEY_INTERPOLATION,
          RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      Image scaled = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      graphics.drawImage(scaled, 0, 0, width, height, null);
    } finally {
      graphics.dispose();
    }
    return resized;
  }

  private BufferedImage mergeImages(List<BufferedImage> images) {
    int width = images.stream().mapToInt(BufferedImage::getWidth).sum();
    int height = images.stream().mapToInt(BufferedImage::getHeight).max().orElse(0);
    BufferedImage merged = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = merged.createGraphics();
    try {
      int x = 0;
      for (BufferedImage image : images) {
        graphics.drawImage(image, x, 0, null);
        x += image.getWidth();
      }
    } finally {
      graphics.dispose();
    }
    return merged;
  }

  private String encodeJpeg(BufferedImage image) throws IOException {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      if (!javax.imageio.ImageIO.write(image, "jpg", output)) {
        throw new IOException("JPEG writer is unavailable.");
      }
      return Base64.getEncoder().encodeToString(output.toByteArray());
    }
  }

  private String safeMessage(Exception exception) {
    return StringUtils.hasText(exception.getMessage())
        ? exception.getMessage()
        : exception.getClass().getSimpleName();
  }
}
