package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan;

/**
 * Test configuration class for Spring integration
 * Manages test-specific beans and properties
 */
@Configuration
@ComponentScan(basePackages = {"com.example"})
@PropertySource("classpath:test.properties")
public class TestConfiguration {

    // Test-specific configuration beans can be defined here
    
}