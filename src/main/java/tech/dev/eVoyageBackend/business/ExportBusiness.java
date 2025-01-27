package tech.dev.eVoyageBackend.business;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.dev.eVoyageBackend.utils.ExceptionUtils;
import tech.dev.eVoyageBackend.utils.FunctionalError;
import tech.dev.eVoyageBackend.utils.TechnicalError;
import tech.dev.eVoyageBackend.utils.dto.ApiLogsDto;
import tech.dev.eVoyageBackend.utils.dto.LogsDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExportBusiness {


    @Autowired
    private TechnicalError technicalError;

    @Autowired
    private FunctionalError functionalError;


    @Autowired
    private ExceptionUtils exceptionUtils;

    public void exportApiLogsToExcel(
            List<ApiLogsDto> data,
            String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("API Logs");

        // Définir les styles pour les en-têtes
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        // Définir les styles pour les données
        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 10);
        dataStyle.setFont(dataFont);
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        // Créer les en-têtes
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "ID", "Request Time", "Request URL", "Request Method", "Request Headers", "Request Body",
                "Response Time", "Response Status", "Response Body", "Created By", "Created Date",
                "Last Modified By", "Last Modified Date", "Status"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Remplir les données
        int rowNum = 1;
        for (ApiLogsDto dto : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            Cell cell = row.createCell(colNum++);
            cell.setCellValue(dto.getId() != null ? dto.getId() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequestTime());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequestUrl());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequestMethod());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequestHeaders());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequestBody());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getResponseTime());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getResponseStatus() != null ? dto.getResponseStatus() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getResponseBody());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getCreatedBy());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getCreatedDate());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getLastModifiedBy());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getLastModifiedDate());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getStatus());
            cell.setCellStyle(dataStyle);
        }

        // Ajustement automatique des colonnes
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Écrire dans le fichier
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    public void exportLogsToExcel(
            List<LogsDto> data,
            String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Logs");

        // Style des en-têtes
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        // Style pour les données
        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 10);
        dataStyle.setFont(dataFont);
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        // Création des en-têtes
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "ID", "Action Service", "Created At", "Created By", "Date", "ID Status", "IP Address",
                "Is Connexion", "Is Deleted", "Login", "Machine", "Nom", "Prénom", "Request",
                "Response", "Search String", "Status Connection", "Updated At", "Updated By", "URI"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Remplir les données
        int rowNum = 1;
        for (LogsDto dto : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            Cell cell = row.createCell(colNum++);
            cell.setCellValue(dto.getId() != null ? dto.getId() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getActionService());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getCreatedAt());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getCreatedBy() != null ? dto.getCreatedBy() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getDate());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getIdStatus() != null ? dto.getIdStatus() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getIpadress());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getIsConnexion() != null && dto.getIsConnexion() ? "Oui" : "Non");
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getIsDeleted() != null && dto.getIsDeleted() ? "Oui" : "Non");
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getLogin());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getMachine());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getNom());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getPrenom());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getRequest());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getResponse());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getSearchString());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getStatusConnection());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getUpdatedAt());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getUpdatedBy() != null ? dto.getUpdatedBy() : 0);
            cell.setCellStyle(dataStyle);

            cell = row.createCell(colNum++);
            cell.setCellValue(dto.getUri());
            cell.setCellStyle(dataStyle);
        }

        // Ajustement automatique des colonnes
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Écrire dans le fichier
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }



}
