package com.neurozen.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main application class for the Neurozen Platform.
 * This is the entry point for the Spring Boot application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class NeurozenPlatformApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(NeurozenPlatformApplication.class, args);
    }

}

