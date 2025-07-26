@login @smoke
Feature: User Login
  As a user
  I want to be able to login to the application
  So that I can access my account

  Background:
    Given the user is on the login page

  @positive
  Scenario: Successful login with valid credentials
    When the user enters valid credentials
    Then the user should be redirected to the dashboard

  @positive @data-driven
  Scenario Outline: Login with different valid credentials
    When the user enters username "<username>" and password "<password>"
    Then the user should be redirected to the dashboard

    Examples:
      | username | password |
      | admin    | admin123 |
      | user1    | pass123  |

  @negative
  Scenario: Login with invalid credentials
    When the user enters username "invalid" and password "wrong"
    Then the user should see an error message "Invalid username or password"

  @negative
  Scenario: Login with empty credentials
    When the user enters username "" and password ""
    Then the user should see an error message "Username and password are required"