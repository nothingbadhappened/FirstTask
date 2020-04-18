package com.actions.common;
import com.helpers.configuration.ConfigFileReader;
import org.springframework.stereotype.Component;

import static com.step_definitions.Hooks.driver;

@Component
public class NavigateHome {
    public static void navigateToHomePage(){
        driver.get(ConfigFileReader.getUrl());
    }
}