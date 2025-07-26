package com.example.tasks;

import com.example.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Screenplay pattern task for login functionality
 * Provides a high-level abstraction for login operations
 */
public class Login implements Task {

    private final String username;
    private final String password;

    // Page elements as targets
    public static final Target USERNAME_FIELD = Target.the("username field").locatedBy("#username");
    public static final Target PASSWORD_FIELD = Target.the("password field").locatedBy("#password");
    public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("#login-button");

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password) {
        return instrumented(Login.class, username, password);
    }

    public static Login withValidCredentials() {
        return instrumented(Login.class, "admin", "admin123");
    }

    @Override
    @Step("{0} logs in with username '#username' and password '#password'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(username).into(USERNAME_FIELD),
            Enter.theValue(password).into(PASSWORD_FIELD),
            Click.on(LOGIN_BUTTON)
        );
    }
}