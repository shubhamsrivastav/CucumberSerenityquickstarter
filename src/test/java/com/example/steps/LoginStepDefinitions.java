package com.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;

/**
 * Step definitions for login functionality
 * Uses Serenity BDD annotations for better reporting
 */
public class LoginStepDefinitions extends UIInteractionSteps {

    @Given("the user is on the login page")
    @Step("Navigate to login page")
    public void the_user_is_on_the_login_page() {
        // Implementation to navigate to the login page
        System.out.println("Navigating to login page");
        // Example: getDriver().get("https://example.com/login");
    }

    @When("the user enters valid credentials")
    @Step("Enter valid credentials")
    public void the_user_enters_valid_credentials() {
        // Implementation for entering username and password
        System.out.println("Entering valid credentials");
        // Example: 
        // $("#username").type("testuser");
        // $("#password").type("testpass");
    }

    @When("the user enters username {string} and password {string}")
    @Step("Enter username '{0}' and password '{1}'")
    public void the_user_enters_credentials(String username, String password) {
        System.out.println("Entering username: " + username + " and password: " + password);
        // Implementation for parameterized credentials
    }

    @Then("the user should be redirected to the dashboard")
    @Step("Verify redirection to dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        // Implementation to verify redirection to the dashboard
        System.out.println("Verifying redirection to dashboard");
        // Example: assertThat(getDriver().getCurrentUrl()).contains("/dashboard");
    }

    @Then("the user should see an error message {string}")
    @Step("Verify error message: '{0}'")
    public void the_user_should_see_error_message(String expectedMessage) {
        System.out.println("Verifying error message: " + expectedMessage);
        // Implementation to verify error message
    }
}