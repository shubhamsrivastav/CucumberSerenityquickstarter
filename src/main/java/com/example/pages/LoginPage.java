package com.example.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Page Object for Login page
 * Follows Page Object Model pattern with Serenity BDD
 */
@DefaultUrl("/login")
public class LoginPage extends PageObject {

    @FindBy(id = "username")
    private WebElementFacade usernameField;

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(id = "login-button")
    private WebElementFacade loginButton;

    @FindBy(css = ".error-message")
    private WebElementFacade errorMessage;

    @FindBy(css = ".welcome-message")
    private WebElementFacade welcomeMessage;

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessage.isDisplayed();
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public void waitForPageToLoad() {
        waitFor(loginButton).toBeVisible();
    }
}