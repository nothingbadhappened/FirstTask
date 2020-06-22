package com.endava.actions.common;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class NavigateHome {

    @Autowired
    private WebDriver driver;

    @Autowired
    private Environment environment;

    public void navigateToHomePage() {
        driver.get(environment.getProperty("url"));
    }
}