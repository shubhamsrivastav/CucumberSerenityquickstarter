```
serenity-cucumber-jdk21/
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── example
    │               └── (your application code)
    └── test
        ├── java
        │   └── com
        │       └── example
        │           ├── steps
        │           │   └── LoginStepDefinitions.java
        │           └── CucumberTestSuite.java
        └── resources
            ├── features
            │   └── login.feature
            └── serenity.conf