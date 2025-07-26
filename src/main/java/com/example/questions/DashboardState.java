package com.example.questions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Screenplay pattern questions for dashboard state verification
 * Provides methods to query the current state of the dashboard
 */
public class DashboardState {

    public static final Target DASHBOARD_TITLE = Target.the("dashboard title").locatedBy(".dashboard-title");
    public static final Target USER_PROFILE = Target.the("user profile").locatedBy(".user-profile");
    public static final Target NAVIGATION_MENU = Target.the("navigation menu").locatedBy(".navigation-menu");

    public static Question<Boolean> isDisplayed() {
        return new Question<Boolean>() {
            @Override
            @Step("{0} checks if dashboard is displayed")
            public Boolean answeredBy(Actor actor) {
                return Visibility.of(DASHBOARD_TITLE).answeredBy(actor);
            }
        };
    }

    public static Question<String> title() {
        return new Question<String>() {
            @Override
            @Step("{0} reads the dashboard title")
            public String answeredBy(Actor actor) {
                return Text.of(DASHBOARD_TITLE).answeredBy(actor);
            }
        };
    }

    public static Question<Boolean> hasUserProfile() {
        return new Question<Boolean>() {
            @Override
            @Step("{0} checks if user profile is visible")
            public Boolean answeredBy(Actor actor) {
                return Visibility.of(USER_PROFILE).answeredBy(actor);
            }
        };
    }

    public static Question<Boolean> hasNavigationMenu() {
        return new Question<Boolean>() {
            @Override
            @Step("{0} checks if navigation menu is visible")
            public Boolean answeredBy(Actor actor) {
                return Visibility.of(NAVIGATION_MENU).answeredBy(actor);
            }
        };
    }
}