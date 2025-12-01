package com.neurozen.platform.shared.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * DataSource configuration for production environments.
 * Parses DATABASE_URL from environment variables and converts it to JDBC
 * format.
 */
@Configuration
@Profile("prod")
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        // Read DATABASE_URL directly from environment variables
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl == null || databaseUrl.isEmpty()) {
            throw new IllegalStateException(
                    "DATABASE_URL environment variable is not set. " +
                            "Please configure it in Render dashboard (Settings -> Environment)");
        }

        HikariConfig config = new HikariConfig();

        try {
            // Parse DATABASE_URL format: postgresql://username:password@host:port/database
            // Remove the postgresql:// prefix
            String urlWithoutProtocol = databaseUrl.replace("postgresql://", "");

            // Split by @ to separate credentials from host
            String[] parts = urlWithoutProtocol.split("@");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "Invalid DATABASE_URL format. Expected: postgresql://username:password@host:port/database");
            }

            // Extract username and password
            String credentials = parts[0];
            String[] credParts = credentials.split(":");
            if (credParts.length != 2) {
                throw new IllegalArgumentException("Invalid credentials format in DATABASE_URL");
            }
            String username = credParts[0];
            String password = credParts[1];

            // Extract host, port, and database
            String hostAndDb = parts[1];
            String[] hostDbParts = hostAndDb.split("/");
            if (hostDbParts.length != 2) {
                throw new IllegalArgumentException("Invalid host/database format in DATABASE_URL");
            }

            String hostAndPort = hostDbParts[0];
            String database = hostDbParts[1];

            // Extract host and port
            String host;
            String port = "5432"; // Default PostgreSQL port
            if (hostAndPort.contains(":")) {
                String[] hostPortParts = hostAndPort.split(":");
                host = hostPortParts[0];
                port = hostPortParts[1];
            } else {
                host = hostAndPort;
            }

            // Build JDBC URL
            String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s", host, port, database);

            // Configure HikariCP
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName("org.postgresql.Driver");

            // Connection pool settings
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);

            // SSL settings for Render
            config.addDataSourceProperty("ssl", "true");
            config.addDataSourceProperty("sslmode", "require");

            System.out.println("✅ Database configuration successful:");
            System.out.println("   JDBC URL: " + jdbcUrl);
            System.out.println("   Username: " + username);
            System.out.println("   Host: " + host);
            System.out.println("   Port: " + port);
            System.out.println("   Database: " + database);

            return new HikariDataSource(config);

        } catch (Exception e) {
            System.err.println("❌ Failed to parse DATABASE_URL: " + e.getMessage());
            System.err.println("   DATABASE_URL format should be: postgresql://username:password@host:port/database");
            throw new IllegalStateException("Failed to configure database connection", e);
        }
    }
}
