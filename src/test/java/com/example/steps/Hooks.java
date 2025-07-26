package com.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.webdriver.driverservice.DriverServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cucumber hooks for setup and teardown operations
 * Manages test lifecycle and Serenity BDD integration
 */
public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("Starting scenario: {}", scenario.getName());
        LOGGER.info("Scenario tags: {}", scenario.getSourceTagNames());
        
        // Set scenario name in Serenity context
        Serenity.recordReportData().withTitle(scenario.getName());
        
        // Additional setup based on tags
        if (scenario.getSourceTagNames().contains("@web")) {
            LOGGER.info("Setting up web driver for scenario");
            // Web-specific setup
        }
        
        if (scenario.getSourceTagNames().contains("@api")) {
            LOGGER.info("Setting up API client for scenario");
            // API-specific setup
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        LOGGER.info("Finishing scenario: {} - Status: {}", 
                   scenario.getName(), scenario.getStatus());
        
        if (scenario.isFailed()) {
            LOGGER.error("Scenario failed: {}", scenario.getName());
            // Take screenshot if web test
            if (scenario.getSourceTagNames().contains("@web")) {
                // Screenshot is automatically taken by Serenity
                LOGGER.info("Screenshot captured for failed scenario");
            }
        }
        
        // Clean up resources
        try {
            // Close any open resources
            LOGGER.info("Cleaning up resources for scenario: {}", scenario.getName());
        } catch (Exception e) {
            LOGGER.warn("Error during cleanup: {}", e.getMessage());
        }
    }

    @Before("@database")
    public void setUpDatabase() {
        LOGGER.info("Setting up database for scenario");
        // Database setup logic
    }

    @After("@database")
    public void cleanUpDatabase() {
        LOGGER.info("Cleaning up database after scenario");
        // Database cleanup logic
    }

    @Before("@api")
    public void setUpApiClient() {
        LOGGER.info("Setting up API client");
        // API client setup
    }

    @After("@api")
    public void cleanUpApiClient() {
        LOGGER.info("Cleaning up API client");
        // API client cleanup
    }
}