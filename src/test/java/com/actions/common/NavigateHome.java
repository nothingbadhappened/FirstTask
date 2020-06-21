package com.actions.common;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class NavigateHome {

    @Autowired
    WebDriver driver;

    @Autowired
    Environment environment;

    public void navigateToHomePage(){
        driver.get(environment.getProperty("url"));
    }
}