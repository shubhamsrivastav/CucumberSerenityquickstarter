package com.example.steps;

import com.example.utils.ExcelUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Step definitions demonstrating how to use ExcelUtils in Cucumber tests
 */
public class ExcelDataSteps {

    private Map<Integer, Map<String, String>> testData;
    private List<Map<String, String>> testDataList;
    private String currentExcelFile;

    @Given("I have test data in Excel file {string}")
    public void i_have_test_data_in_excel_file(String excelFilePath) {
        this.currentExcelFile = excelFilePath;
        try {
            // Read all data from Excel file
            this.testData = ExcelUtils.readExcelToMapOfMaps(excelFilePath);
            this.testDataList = ExcelUtils.readExcelToListOfMaps(excelFilePath, null);
            
            System.out.println("Loaded " + testData.size() + " rows of test data from: " + excelFilePath);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + excelFilePath, e);
        }
    }

    @Given("I have test data in Excel file {string} from sheet {string}")
    public void i_have_test_data_in_excel_file_from_sheet(String excelFilePath, String sheetName) {
        this.currentExcelFile = excelFilePath;
        try {
            // Read data from specific sheet
            this.testData = ExcelUtils.readExcelToMapOfMaps(excelFilePath, sheetName);
            this.testDataList = ExcelUtils.readExcelToListOfMaps(excelFilePath, sheetName);
            
            System.out.println("Loaded " + testData.size() + " rows of test data from sheet '" + 
                             sheetName + "' in file: " + excelFilePath);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + excelFilePath + ", sheet: " + sheetName, e);
        }
    }

    @When("I retrieve data from row {int}")
    public void i_retrieve_data_from_row(int rowNumber) {
        Map<String, String> rowData = testData.get(rowNumber);
        
        if (rowData != null) {
            System.out.println("Row " + rowNumber + " data:");
            rowData.forEach((column, value) -> 
                System.out.println("  " + column + ": " + value));
        } else {
            System.out.println("No data found for row: " + rowNumber);
        }
    }

    @When("I retrieve value from row {int} and column {string}")
    public void i_retrieve_value_from_row_and_column(int rowNumber, String columnName) {
        Map<String, String> rowData = testData.get(rowNumber);
        
        if (rowData != null && rowData.containsKey(columnName)) {
            String cellValue = rowData.get(columnName);
            System.out.println("Value at row " + rowNumber + ", column '" + columnName + "': " + cellValue);
        } else {
            System.out.println("No data found for row " + rowNumber + ", column '" + columnName + "'");
        }
    }

    @When("I iterate through all test data")
    public void i_iterate_through_all_test_data() {
        System.out.println("Iterating through all test data:");
        
        // Using Map of Maps approach
        testData.forEach((rowNumber, rowData) -> {
            System.out.println("Row " + rowNumber + ":");
            rowData.forEach((column, value) -> 
                System.out.println("  " + column + ": " + value));
        });
        
        // Alternative: Using List of Maps approach
        System.out.println("\nAlternative iteration using List:");
        for (int i = 0; i < testDataList.size(); i++) {
            Map<String, String> row = testDataList.get(i);
            System.out.println("Data row " + (i + 1) + ": " + row);
        }
    }

    @Then("I should be able to access the Excel data")
    public void i_should_be_able_to_access_the_excel_data() {
        if (testData == null || testData.isEmpty()) {
            throw new AssertionError("No test data was loaded from Excel file");
        }
        
        System.out.println("Successfully accessed Excel data with " + testData.size() + " rows");
    }

    @Then("I should be able to get sheet information")
    public void i_should_be_able_to_get_sheet_information() {
        try {
            List<String> sheetNames = ExcelUtils.getSheetNames(currentExcelFile);
            int rowCount = ExcelUtils.getRowCount(currentExcelFile, null);
            
            System.out.println("Sheet names: " + sheetNames);
            System.out.println("Row count in first sheet: " + rowCount);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to get sheet information", e);
        }
    }

    // Helper methods for use in other step definitions
    
    /**
     * Get test data for a specific row
     * @param rowNumber Row number (1-based)
     * @return Map of column names to values
     */
    public Map<String, String> getTestDataForRow(int rowNumber) {
        return testData != null ? testData.get(rowNumber) : null;
    }

    /**
     * Get value from specific row and column
     * @param rowNumber Row number (1-based)
     * @param columnName Column name
     * @return Cell value as String
     */
    public String getTestDataValue(int rowNumber, String columnName) {
        Map<String, String> rowData = getTestDataForRow(rowNumber);
        return rowData != null ? rowData.get(columnName) : null;
    }

    /**
     * Get all test data as List for easier iteration
     * @return List of Maps representing all rows
     */
    public List<Map<String, String>> getAllTestData() {
        return testDataList;
    }
}