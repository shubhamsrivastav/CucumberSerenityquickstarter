package com.example.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Page Object for Dashboard page
 * Represents the main dashboard after successful login
 */
@DefaultUrl("/dashboard")
public class DashboardPage extends PageObject {

    @FindBy(css = ".dashboard-title")
    private WebElementFacade dashboardTitle;

    @FindBy(css = ".user-profile")
    private WebElementFacade userProfile;

    @FindBy(id = "logout-button")
    private WebElementFacade logoutButton;

    @FindBy(css = ".navigation-menu")
    private WebElementFacade navigationMenu;

    @FindBy(css = ".dashboard-widgets")
    private WebElementFacade dashboardWidgets;

    public boolean isDashboardDisplayed() {
        return dashboardTitle.isDisplayed();
    }

    public String getDashboardTitle() {
        return dashboardTitle.getText();
    }

    public boolean isUserProfileDisplayed() {
        return userProfile.isDisplayed();
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public boolean isLogoutButtonVisible() {
        return logoutButton.isVisible();
    }

    public boolean isNavigationMenuDisplayed() {
        return navigationMenu.isDisplayed();
    }

    public boolean areDashboardWidgetsDisplayed() {
        return dashboardWidgets.isDisplayed();
    }

    public void waitForDashboardToLoad() {
        waitFor(dashboardTitle).toBeVisible();
        waitFor(navigationMenu).toBeVisible();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}