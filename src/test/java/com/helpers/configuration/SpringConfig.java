package com.helpers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.*"})

public class SpringConfig {
    @Bean
    DataSource MySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://remotemysql.com:3306/nrmY0gpABA");
        dataSource.setUsername("nrmY0gpABA");
        dataSource.setPassword("9wNvfmyRVd");

        return dataSource;
    }
}
