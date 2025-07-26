package com.example.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;

/**
 * Main test runner for Cucumber tests
 * Configures Serenity BDD integration and test execution
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME, 
    value = "pretty, summary, html:target/cucumber-reports, json:target/cucumber-reports/Cucumber.json, " +
            "junit:target/cucumber-reports/Cucumber.xml, " +
            "net.serenity-bdd.cucumber.CucumberWithSerenity"
)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
public class CucumberTestSuite {
}