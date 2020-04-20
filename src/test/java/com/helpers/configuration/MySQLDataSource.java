package com.helpers.configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class MySQLDataSource {
    DriverManagerDataSource MySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://remotemysql.com:3306/nrmY0gpABA");
        dataSource.setUsername("nrmY0gpABA");
        dataSource.setPassword("9wNvfmyRVd");

        return dataSource;
    }
}