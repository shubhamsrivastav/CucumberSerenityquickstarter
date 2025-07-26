# Project Structure - Modern Test Automation Framework

This project follows the latest testing standards and best practices for a Serenity BDD + Cucumber test automation framework.

## Directory Structure

```
cuucmber/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               ├── pages/              # Page Object Model classes
│   │               │   ├── LoginPage.java
│   │               │   └── DashboardPage.java
│   │               ├── tasks/              # Screenplay pattern tasks
│   │               │   └── Login.java
│   │               ├── questions/          # Screenplay pattern questions
│   │               │   └── DashboardState.java
│   │               └── utils/              # Utility classes
│   │                   ├── ExcelUtils.java
│   │                   └── README.md
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └��─ example/
│       │           ├── runners/            # Test runners
│       │           │   └── CucumberTestSuite.java
│       │           ├── steps/              # Step definitions
│       │           │   ├── LoginStepDefinitions.java
│       │           │   ├── ExcelDataSteps.java
│       │           │   └── Hooks.java
│       │           ├── config/             # Test configuration
│       │           │   └── TestConfiguration.java
│       │           └── utils/              # Test utilities
│       │               └── ExcelUtilsTest.java
│       └── resources/
│           ├── features/                   # Cucumber feature files
│           │   ├── login.feature
│           │   └── excel-data.feature
│           ├── test-data/                  # Test data files
│           │   └── login-data.xlsx
│           ├── test.properties             # Test properties
│           └── logback-test.xml            # Logging configuration
├── target/                                 # Build output (auto-generated)
├── pom.xml                                 # Maven configuration
├── serenity.conf                          # Serenity BDD configuration
├── README.md                              # Project documentation
└── PROJECT_STRUCTURE.md                   # This file
```

## Architecture Patterns

### 1. Page Object Model (POM)
- **Location**: `src/main/java/com/example/pages/`
- **Purpose**: Encapsulate page elements and actions
- **Example**: `LoginPage.java`, `DashboardPage.java`

### 2. Screenplay Pattern
- **Tasks**: `src/main/java/com/example/tasks/` - High-level business actions
- **Questions**: `src/main/java/com/example/questions/` - State verification queries
- **Purpose**: More maintainable and readable test code

### 3. Step Definitions
- **Location**: `src/test/java/com/example/steps/`
- **Purpose**: Bridge between Gherkin scenarios and Java code
- **Includes**: Hooks for setup/teardown operations

## Key Features

### Modern Testing Standards
1. **Separation of Concerns**: Clear separation between page objects, business logic, and test data
2. **Screenplay Pattern**: Actor-based testing for better readability
3. **Dependency Injection**: Spring integration for better test management
4. **Configuration Management**: Environment-specific configurations
5. **Comprehensive Logging**: Structured logging with Logback
6. **Data-Driven Testing**: Excel integration for test data management

### Test Organization
1. **Feature-Based Structure**: Tests organized by business features
2. **Tag-Based Execution**: Support for running specific test subsets
3. **Parallel Execution**: Configuration for parallel test execution
4. **Retry Mechanism**: Built-in retry for flaky tests

### Reporting and Monitoring
1. **Serenity Reports**: Rich HTML reports with screenshots
2. **Multiple Formats**: Support for HTML, JSON, and XML reports
3. **Step-by-Step Details**: Detailed test execution logs
4. **Screenshot Capture**: Automatic screenshots on failures

## Configuration Files

### serenity.conf
- WebDriver configuration
- Environment-specific settings
- Report configuration
- Parallel execution settings

### test.properties
- Application URLs
- Test credentials
- Timeout configurations
- Environment variables

### logback-test.xml
- Logging levels
- Output formats
- File rotation policies

## Best Practices Implemented

1. **Clean Code**: Meaningful names, single responsibility principle
2. **DRY Principle**: Reusable components and utilities
3. **SOLID Principles**: Well-structured, maintainable code
4. **Test Data Management**: External test data files
5. **Environment Abstraction**: Easy switching between environments
6. **Error Handling**: Comprehensive exception handling
7. **Documentation**: Inline comments and README files

## Usage Guidelines

### Running Tests
```bash
# Run all tests
mvn clean test

# Run specific tags
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run with specific environment
mvn clean test -Denvironment=staging

# Generate reports
mvn serenity:aggregate
```

### Adding New Tests
1. Create feature file in `src/test/resources/features/`
2. Implement step definitions in `src/test/java/com/example/steps/`
3. Create page objects if needed in `src/main/java/com/example/pages/`
4. Add test data to `src/test/resources/test-data/`

### Extending the Framework
1. Add new utilities to `src/main/java/com/example/utils/`
2. Create new tasks/questions for Screenplay pattern
3. Add environment-specific configurations
4. Implement new hooks for specific test types

This structure provides a solid foundation for scalable, maintainable test automation that follows industry best practices and modern testing standards.