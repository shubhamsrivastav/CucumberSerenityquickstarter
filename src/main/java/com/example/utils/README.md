# ExcelUtils - Excel Data Handling Utility

## Overview
The `ExcelUtils` class provides comprehensive functionality for reading Excel files and converting data into Java data structures that are easy to work with in test automation scenarios.

## Features
- Read Excel files (.xlsx and .xls formats)
- Convert Excel data to Map of Maps structure (row number → column data)
- Convert Excel data to List of Maps for easier iteration
- Read specific rows and cells
- Get sheet information (names, row counts)
- Handle different cell types (String, Numeric, Boolean, Date, Formula)

## Dependencies
The utility uses Apache POI library for Excel operations. Make sure these dependencies are in your `pom.xml`:

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.5</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

## Usage Examples

### 1. Basic Excel Reading
```java
// Read entire Excel file (first sheet)
Map<Integer, Map<String, String>> data = ExcelUtils.readExcelToMapOfMaps("path/to/file.xlsx");

// Read specific sheet
Map<Integer, Map<String, String>> data = ExcelUtils.readExcelToMapOfMaps("path/to/file.xlsx", "SheetName");
```

### 2. Data Structure Explanation
The returned `Map<Integer, Map<String, String>>` structure:
- **Outer Map Key**: Row number (1-based, excluding header row)
- **Outer Map Value**: Map representing the row data
- **Inner Map Key**: Column name (from header row)
- **Inner Map Value**: Cell value as String

Example:
```
Row 1: {username=john_doe, password=secret123, email=john@example.com}
Row 2: {username=jane_smith, password=mypass456, email=jane@example.com}
```

### 3. Accessing Data
```java
// Get all data
Map<Integer, Map<String, String>> allData = ExcelUtils.readExcelToMapOfMaps("test-data.xlsx");

// Access specific row
Map<String, String> row1 = allData.get(1);

// Access specific cell
String username = allData.get(1).get("username");

// Alternative: Read specific row directly
Map<String, String> rowData = ExcelUtils.readExcelRow("test-data.xlsx", "Sheet1", 1);
```

### 4. List-based Iteration
```java
// Convert to List for easier iteration
List<Map<String, String>> dataList = ExcelUtils.readExcelToListOfMaps("test-data.xlsx", "Sheet1");

// Iterate through all rows
for (Map<String, String> row : dataList) {
    String username = row.get("username");
    String password = row.get("password");
    // Use the data...
}
```

### 5. Utility Methods
```java
// Get all sheet names
List<String> sheets = ExcelUtils.getSheetNames("test-data.xlsx");

// Get row count
int rowCount = ExcelUtils.getRowCount("test-data.xlsx", "Sheet1");

// Print data for debugging
ExcelUtils.printExcelData(allData);
```

## Excel File Format Requirements
1. **Header Row**: First row must contain column headers
2. **Data Rows**: Subsequent rows contain the actual test data
3. **Supported Formats**: .xlsx and .xls files
4. **Cell Types**: Supports String, Numeric, Boolean, Date, and Formula cells

## Example Excel Structure
```
| username  | password  | email              | active |
|-----------|-----------|-------------------|--------|
| john_doe  | secret123 | john@example.com  | true   |
| jane_smith| mypass456 | jane@example.com  | false  |
| bob_wilson| test789   | bob@example.com   | true   |
```

## Integration with Cucumber
The utility can be easily integrated with Cucumber step definitions:

```java
@Given("I have test data in Excel file {string}")
public void loadTestData(String filePath) {
    testData = ExcelUtils.readExcelToMapOfMaps(filePath);
}

@When("I use data from row {int}")
public void useDataFromRow(int rowNumber) {
    Map<String, String> rowData = testData.get(rowNumber);
    String username = rowData.get("username");
    String password = rowData.get("password");
    // Use the data in your test steps...
}
```

## Error Handling
The utility throws `IOException` for file-related errors and `IllegalArgumentException` for invalid parameters. Always wrap calls in try-catch blocks:

```java
try {
    Map<Integer, Map<String, String>> data = ExcelUtils.readExcelToMapOfMaps("test-data.xlsx");
    // Use data...
} catch (IOException e) {
    System.err.println("Failed to read Excel file: " + e.getMessage());
}
```

## Best Practices
1. **File Paths**: Use relative paths from project root or absolute paths
2. **Resource Files**: Place Excel files in `src/test/resources` for test data
3. **Error Handling**: Always handle IOException when reading files
4. **Memory**: For large Excel files, consider reading data in chunks
5. **Thread Safety**: Create new instances for concurrent access

## Sample Test Data File
Create a sample Excel file at `src/test/resources/test-data.xlsx` with the following structure:

| username | password | email | role |
|----------|----------|-------|------|
| admin | admin123 | admin@test.com | administrator |
| user1 | pass123 | user1@test.com | user |
| user2 | pass456 | user2@test.com | user |

This utility class provides a robust foundation for data-driven testing with Excel files in your Serenity BDD and Cucumber test automation framework.