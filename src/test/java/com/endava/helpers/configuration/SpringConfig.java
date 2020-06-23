package com.endava.helpers.configuration;

import com.endava.helpers.util.WebDriverFactory;
import com.endava.pageObjects.PageFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.endava"})
@PropertySource("classpath:configurations/configuration.properties")
public class SpringConfig {

    private static final Logger log = LoggerFactory.getLogger(SpringConfig.class);

    @Autowired
    private Environment environment;

    @Bean(name = "driver")
    public WebDriver driver() {
        log.debug("Spring bean \"driver\" is starting:");
        return WebDriverFactory.getDriver(environment.getProperty("driver"));
    }

    @Bean
    public WebDriverWait wait(@NotNull WebDriver driver) {
        System.out.println("Setting WebDriver Wait for " + driver.toString());
        return new WebDriverWait(driver, 10, 1000);
    }

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
