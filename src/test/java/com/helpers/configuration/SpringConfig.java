package com.helpers.configuration;

import com.helpers.util.WebDriverFactory;
import com.pageObjects.PageFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.*"})
@PropertySource("classpath:configurations/configuration.properties")

public class SpringConfig {

    private static final Logger log = LoggerFactory.getLogger(PageFactory.class);

    @Autowired
    Environment environment;

    @Bean(name = "driver", destroyMethod = "quit")
    WebDriver driver() {
        log.debug("Spring bean \"driver\" is starting:");
        WebDriverFactory driverFactory = new WebDriverFactory();
        return driverFactory.getDriver(environment.getProperty("driver"));
    }

    @Bean
    public WebDriverWait wait(@NotNull WebDriver driver) {
        System.out.println("Setting WebDriver Wait for " + driver.toString());
        return new WebDriverWait(driver, 10, 1000);
    }

    @Bean
    DataSource setMySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://35.197.223.185:3306/AUTOMATION_PRACTICE");
        dataSource.setUsername("testdbuser");
        dataSource.setPassword("Test1234!");

        return dataSource;
    }
}
