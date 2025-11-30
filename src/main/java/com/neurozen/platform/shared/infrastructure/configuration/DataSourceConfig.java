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
 * Parses DATABASE_URL from environment variables and converts it to JDBC format.
 */
@Configuration
@Profile("prod")
public class DataSourceConfig {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // Convert postgresql:// to jdbc:postgresql://
            String jdbcUrl = databaseUrl;
            if (databaseUrl.startsWith("postgresql://")) {
                jdbcUrl = "jdbc:" + databaseUrl;
            } else if (!databaseUrl.startsWith("jdbc:")) {
                jdbcUrl = "jdbc:postgresql://" + databaseUrl;
            }
            
            config.setJdbcUrl(jdbcUrl);
        }
        
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        
        return new HikariDataSource(config);
    }
}

