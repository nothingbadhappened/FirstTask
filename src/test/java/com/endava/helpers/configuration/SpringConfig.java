package com.endava.helpers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.endava"})
@PropertySource("classpath:configurations/configuration.properties")
public class SpringConfig {

    @Bean
    public DataSource setMySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://35.197.223.185:3306/AUTOMATION_PRACTICE");
        dataSource.setUsername("testdbuser");
        dataSource.setPassword("Test1234!");
        return dataSource;
    }

}