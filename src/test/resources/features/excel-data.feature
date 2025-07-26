Feature: Excel Data Handling
  As a test automation engineer
  I want to read test data from Excel files
  So that I can use data-driven testing approaches

  Background:
    Given I have test data in Excel file "src/test/resources/test-data.xlsx"

  Scenario: Read and access Excel data
    When I retrieve data from row 1
    And I retrieve value from row 1 and column "username"
    Then I should be able to access the Excel data

  Scenario: Iterate through all Excel data
    When I iterate through all test data
    Then I should be able to access the Excel data

  Scenario: Get Excel file information
    Then I should be able to get sheet information

  Scenario: Read data from specific sheet
    Given I have test data in Excel file "src/test/resources/test-data.xlsx" from sheet "LoginData"
    When I retrieve data from row 1
    Then I should be able to access the Excel data

  # Example of data-driven testing using Excel data
  Scenario Outline: Login with Excel data
    Given I have test data in Excel file "src/test/resources/login-data.xlsx"
    When I retrieve value from row <rowNumber> and column "username"
    And I retrieve value from row <rowNumber> and column "password"
    Then I should be able to access the Excel data

    Examples:
      | rowNumber |
      | 1         |
      | 2         |
      | 3         |