package com.leads.microcube.verifidadmin.log;

import com.leads.microcube.verifidadmin.log.query.UserActivityLogExportResponse;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogFilter;
import com.leads.microcube.verifidadmin.log.query.UserActivityLogResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class UserActivityLogExcelGenerator {

  private static final String[] HEADERS = {
    "User ID",
    "ActivitySlNo",
    "TrackingNo",
    "StepId",
    "ActionType",
    "ActionParticulars",
    "ActionDate",
    "ActionTerminalIp"
  };
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public UserActivityLogExportResponse generate(
      List<UserActivityLogResponse> activities,
      UserActivityLogFilter filter) {
    SXSSFWorkbook workbook = new SXSSFWorkbook(100);
    workbook.setCompressTempFiles(true);
    try (workbook; ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      Sheet sheet = workbook.createSheet("User Activity Log");
      writeTitle(sheet, filter);
      writeHeaders(sheet, workbook);
      writeActivities(sheet, activities);
      setColumnWidths(sheet);
      workbook.write(outputStream);
      return UserActivityLogExportResponse.builder()
          .pdfData(Base64.getEncoder().encodeToString(outputStream.toByteArray()))
          .documentName(createDocumentName(filter))
          .build();
    } catch (IOException exception) {
      throw new IllegalStateException("Unable to generate user activity Excel report.", exception);
    } finally {
      workbook.dispose();
    }
  }

  private void writeTitle(Sheet sheet, UserActivityLogFilter filter) {
    Row titleRow = sheet.createRow(0);
    titleRow.createCell(0).setCellValue("User Activity Log");
    Row periodRow = sheet.createRow(1);
    String dateFrom = filter.getDateFrom() == null ? "" : filter.getDateFrom().toString();
    String dateTo = filter.getDateTo() == null ? "" : filter.getDateTo().toString();
    periodRow.createCell(0).setCellValue(dateFrom + " - " + dateTo);
  }

  private void writeHeaders(Sheet sheet, SXSSFWorkbook workbook) {
    Row headerRow = sheet.createRow(3);
    CellStyle headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
    for (int index = 0; index < HEADERS.length; index++) {
      Cell cell = headerRow.createCell(index);
      cell.setCellValue(HEADERS[index]);
      cell.setCellStyle(headerStyle);
    }
  }

  private void writeActivities(
      Sheet sheet,
      List<UserActivityLogResponse> activities) {
    int rowIndex = 4;
    for (UserActivityLogResponse activity : activities) {
      Row row = sheet.createRow(rowIndex++);
      row.createCell(0).setCellValue(value(activity.getUserId()));
      row.createCell(1).setCellValue(number(activity.getActivitySlNo()));
      row.createCell(2).setCellValue(number(activity.getTrackingNo()));
      row.createCell(3).setCellValue(number(activity.getStepId()));
      row.createCell(4).setCellValue(value(activity.getActionType()));
      row.createCell(5).setCellValue(value(activity.getActionParticulars()));
      row.createCell(6).setCellValue(formatDate(activity));
      row.createCell(7).setCellValue(value(activity.getActionTerminalIp()));
    }
  }

  private void setColumnWidths(Sheet sheet) {
    int[] widths = {20, 16, 18, 12, 14, 45, 22, 24};
    for (int index = 0; index < widths.length; index++) {
      sheet.setColumnWidth(index, widths[index] * 256);
    }
  }

  private String createDocumentName(UserActivityLogFilter filter) {
    String information =
        Stream.of(
                filter.getTrackingNo(),
                filter.getUserId(),
                filter.getDateFrom(),
                filter.getDateTo())
            .filter(value -> value != null && !"0".equals(value.toString()))
            .map(Object::toString)
            .reduce((left, right) -> left + "_" + right)
            .orElse("");
    return "User Activity Log (" + information + ")";
  }

  private String formatDate(UserActivityLogResponse activity) {
    return activity.getActionDate() == null
        ? ""
        : activity.getActionDate().format(DATE_TIME_FORMATTER);
  }

  private String value(String value) {
    return value == null ? "" : value;
  }

  private double number(Number value) {
    return value == null ? 0 : value.doubleValue();
  }
}
