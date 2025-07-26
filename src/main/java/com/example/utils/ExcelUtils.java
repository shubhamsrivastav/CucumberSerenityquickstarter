package com.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for Excel operations including reading Excel files
 * and converting data to Map of Maps structure
 */
public class ExcelUtils {

    /**
     * Reads Excel file and converts data to Map of Maps
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet to read (optional, reads first sheet if null)
     * @return Map where key is row number and value is Map of column name to cell value
     * @throws IOException if file cannot be read
     */
    public static Map<Integer, Map<String, String>> readExcelToMapOfMaps(String filePath, String sheetName) throws IOException {
        Map<Integer, Map<String, String>> excelData = new LinkedHashMap<>();
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = createWorkbook(filePath, fileInputStream)) {
            
            Sheet sheet = (sheetName != null) ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);
            
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file");
            }
            
            // Get header row (assuming first row contains headers)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("Header row not found in the Excel sheet");
            }
            
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(getCellValueAsString(cell));
            }
            
            // Read data rows
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                
                Map<String, String> rowData = new LinkedHashMap<>();
                
                for (int cellIndex = 0; cellIndex < headers.size(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    String cellValue = (cell != null) ? getCellValueAsString(cell) : "";
                    rowData.put(headers.get(cellIndex), cellValue);
                }
                
                excelData.put(rowIndex, rowData);
            }
        }
        
        return excelData;
    }

    /**
     * Reads Excel file and converts data to Map of Maps (reads first sheet)
     * @param filePath Path to the Excel file
     * @return Map where key is row number and value is Map of column name to cell value
     * @throws IOException if file cannot be read
     */
    public static Map<Integer, Map<String, String>> readExcelToMapOfMaps(String filePath) throws IOException {
        return readExcelToMapOfMaps(filePath, null);
    }

    /**
     * Reads specific row from Excel file
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet to read
     * @param rowNumber Row number to read (1-based, excluding header)
     * @return Map of column name to cell value for the specified row
     * @throws IOException if file cannot be read
     */
    public static Map<String, String> readExcelRow(String filePath, String sheetName, int rowNumber) throws IOException {
        Map<Integer, Map<String, String>> allData = readExcelToMapOfMaps(filePath, sheetName);
        return allData.get(rowNumber);
    }

    /**
     * Gets all sheet names from Excel file
     * @param filePath Path to the Excel file
     * @return List of sheet names
     * @throws IOException if file cannot be read
     */
    public static List<String> getSheetNames(String filePath) throws IOException {
        List<String> sheetNames = new ArrayList<>();
        
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = createWorkbook(filePath, fileInputStream)) {
            
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheetNames.add(workbook.getSheetName(i));
            }
        }
        
        return sheetNames;
    }

    /**
     * Gets the number of rows in a specific sheet (excluding header)
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet
     * @return Number of data rows
     * @throws IOException if file cannot be read
     */
    public static int getRowCount(String filePath, String sheetName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = createWorkbook(filePath, fileInputStream)) {
            
            Sheet sheet = (sheetName != null) ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file");
            }
            
            return sheet.getLastRowNum(); // Returns 0-based index, so actual data rows
        }
    }

    /**
     * Reads Excel data and converts to List of Maps for easier iteration
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet to read
     * @return List of Maps where each Map represents a row
     * @throws IOException if file cannot be read
     */
    public static List<Map<String, String>> readExcelToListOfMaps(String filePath, String sheetName) throws IOException {
        Map<Integer, Map<String, String>> mapOfMaps = readExcelToMapOfMaps(filePath, sheetName);
        return new ArrayList<>(mapOfMaps.values());
    }

    /**
     * Creates appropriate Workbook instance based on file extension
     * @param filePath Path to the Excel file
     * @param fileInputStream FileInputStream of the Excel file
     * @return Workbook instance
     * @throws IOException if workbook cannot be created
     */
    private static Workbook createWorkbook(String filePath, FileInputStream fileInputStream) throws IOException {
        if (filePath.endsWith(".xlsx")) {
            return new XSSFWorkbook(fileInputStream);
        } else if (filePath.endsWith(".xls")) {
            return new HSSFWorkbook(fileInputStream);
        } else {
            throw new IllegalArgumentException("Unsupported file format. Only .xlsx and .xls files are supported.");
        }
    }

    /**
     * Converts cell value to String regardless of cell type
     * @param cell Excel cell
     * @return String representation of cell value
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Handle both integer and decimal numbers
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return String.valueOf(cell.getNumericCellValue());
                } catch (Exception e) {
                    return cell.getStringCellValue();
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    /**
     * Utility method to print Excel data in a readable format
     * @param excelData Map of Maps containing Excel data
     */
    public static void printExcelData(Map<Integer, Map<String, String>> excelData) {
        System.out.println("Excel Data:");
        System.out.println("===========");
        
        for (Map.Entry<Integer, Map<String, String>> rowEntry : excelData.entrySet()) {
            System.out.println("Row " + rowEntry.getKey() + ":");
            for (Map.Entry<String, String> cellEntry : rowEntry.getValue().entrySet()) {
                System.out.println("  " + cellEntry.getKey() + ": " + cellEntry.getValue());
            }
            System.out.println();
        }
    }
}