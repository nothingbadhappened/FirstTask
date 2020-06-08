package com.actions.common;
import com.helpers.configuration.ConfigFileReader;
import org.springframework.stereotype.Component;

import static com.hooks.Hooks.driver;

@Component
public class NavigateHome {
    public static void navigateToHomePage(){
        driver.get(ConfigFileReader.getUrl());
    }
}