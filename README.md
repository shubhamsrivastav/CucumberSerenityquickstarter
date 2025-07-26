# Modern Test Automation Framework

A comprehensive test automation framework built with **Serenity BDD**, **Cucumber**, and **Java 21**, following the latest testing standards and best practices.

## 🚀 Features

- **Serenity BDD Integration**: Rich reporting and test management
- **Cucumber BDD**: Behavior-driven development with Gherkin syntax
- **Page Object Model**: Maintainable page representations
- **Screenplay Pattern**: Actor-based testing for better readability
- **Excel Data Integration**: Data-driven testing with Excel files
- **Multi-Environment Support**: Easy switching between test environments
- **Parallel Execution**: Configurable parallel test execution
- **Comprehensive Reporting**: HTML, JSON, and XML reports
- **Modern Java**: Built with Java 21 features

## 📁 Project Structure

```
src/
├── main/java/com/example/
│   ├── pages/          # Page Object Model classes
│   ├── tasks/          # Screenplay pattern tasks
│   ├── questions/      # Screenplay pattern questions
│   └── utils/          # Utility classes (Excel, etc.)
└── test/
    ├── java/com/example/
    │   ├── runners/    # Test runners
    │   ├── steps/      # Step definitions & hooks
    │   ├── config/     # Test configuration
    │   └── utils/      # Test utilities
    └── resources/
        ├── features/   # Cucumber feature files
        ├── test-data/  # Test data files
        └── *.properties # Configuration files
```

## 🛠️ Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **Chrome/Firefox/Edge** browser

## 🏃‍♂️ Quick Start

### 1. Clone and Setup
```bash
git clone <repository-url>
cd cuucmber
mvn clean compile
```

### 2. Run Tests
```bash
# Run all tests
mvn clean test

# Run specific tags
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run with specific environment
mvn clean test -Denvironment=staging
```

### 3. Generate Reports
```bash
mvn serenity:aggregate
```
Reports will be available at `target/site/serenity/index.html`

## 🧪 Test Execution Options

### Tag-Based Execution
```bash
# Smoke tests only
mvn test -Dcucumber.filter.tags="@smoke"

# Login feature tests
mvn test -Dcucumber.filter.tags="@login"

# Exclude slow tests
mvn test -Dcucumber.filter.tags="not @slow"
```

### Environment-Specific Execution
```bash
# Local environment
mvn test -Denvironment=local

# Staging environment
mvn test -Denvironment=staging

# Production environment
mvn test -Denvironment=production
```

### Browser-Specific Execution
```bash
# Chrome (default)
mvn test -Dwebdriver.driver=chrome

# Firefox
mvn test -Dwebdriver.driver=firefox

# Headless mode
mvn test -Dheadless=true
```

## 📊 Excel Data Integration

The framework includes a powerful Excel utility for data-driven testing:

```java
// Read Excel data
Map<Integer, Map<String, String>> data = ExcelUtils.readExcelToMapOfMaps("test-data.xlsx");

// Use in step definitions
@Given("I have test data from {string}")
public void loadTestData(String excelFile) {
    testData = ExcelUtils.readExcelToMapOfMaps(excelFile);
}
```

## 🎭 Screenplay Pattern Example

```java
// Task
public class Login implements Task {
    public static Login withCredentials(String username, String password) {
        return instrumented(Login.class, username, password);
    }
}

// Question
public class DashboardState {
    public static Question<Boolean> isDisplayed() {
        return actor -> Visibility.of(DASHBOARD_TITLE).answeredBy(actor);
    }
}

// Usage in steps
actor.attemptsTo(Login.withCredentials("admin", "admin123"));
actor.should(seeThat(DashboardState.isDisplayed(), is(true)));
```

## 📝 Writing Tests

### 1. Create Feature File
```gherkin
@login @smoke
Feature: User Login
  Scenario: Successful login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the dashboard
```

### 2. Implement Step Definitions
```java
@Given("the user is on the login page")
public void navigateToLoginPage() {
    loginPage.open();
}
```

### 3. Create Page Objects
```java
@DefaultUrl("/login")
public class LoginPage extends PageObject {
    @FindBy(id = "username")
    private WebElementFacade usernameField;
    
    public void enterUsername(String username) {
        usernameField.type(username);
    }
}
```

## ⚙️ Configuration

### Serenity Configuration (`serenity.conf`)
```hocon
webdriver {
  driver = chrome
  timeouts.implicitlywait = 10000
}

serenity {
  take.screenshots = FOR_FAILURES
  report.encoding = UTF8
}
```

### Test Properties (`test.properties`)
```properties
app.base.url=https://example.com
test.username=admin
test.password=admin123
```

## 📈 Reporting

The framework generates comprehensive reports:

- **Serenity Reports**: Rich HTML reports with screenshots
- **Cucumber Reports**: Standard Cucumber JSON/HTML reports
- **JUnit Reports**: XML reports for CI/CD integration

## 🔧 CI/CD Integration

### GitHub Actions Example
```yaml
- name: Run Tests
  run: mvn clean test -Denvironment=staging

- name: Generate Reports
  run: mvn serenity:aggregate

- name: Publish Reports
  uses: actions/upload-artifact@v3
  with:
    name: test-reports
    path: target/site/serenity/
```

## 🤝 Contributing

1. Follow the existing code structure
2. Add appropriate tags to feature files
3. Include step-by-step documentation
4. Ensure all tests pass before submitting

## 📚 Documentation

- [Project Structure](PROJECT_STRUCTURE.md) - Detailed project organization
- [Excel Utils](src/main/java/com/example/utils/README.md) - Excel integration guide
- [Serenity BDD Docs](https://serenity-bdd.info/) - Official Serenity documentation
- [Cucumber Docs](https://cucumber.io/docs/) - Official Cucumber documentation

## 🐛 Troubleshooting

### Common Issues

1. **WebDriver Issues**: Ensure browser drivers are up to date
2. **Test Data**: Verify Excel files are in correct format
3. **Environment**: Check environment-specific configurations
4. **Dependencies**: Run `mvn clean install` to refresh dependencies

### Debug Mode
```bash
mvn test -Dserenity.logging=VERBOSE -Dwebdriver.driver=chrome
```

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Built with ❤️ using modern testing practices and industry standards**