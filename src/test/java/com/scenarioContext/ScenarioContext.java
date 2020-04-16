package com.scenarioContext;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.pageObjects.Page;
import com.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.step_definitions.Hooks.driver;

// == Scenario Context keeps current state of scenario  ==

@Component
public class ScenarioContext {

    // == Fields ==
    @Autowired
    private static User user;

    private static ScenarioContext instance = null;
    private static Page currentPage;
    private static WebElement webElement;
    private static Scenario scenario;
    private static List<Page> currentPages;
    private static List<WebElement> webElements;

    // == constructor(s) ==
    private ScenarioContext() {
    }

    public static void setInstance(ScenarioContext instance) {
        ScenarioContext.instance = instance;
    }

    // == Getters ==
    public static ScenarioContext getInstance() {
        if (instance == null) {
            return new ScenarioContext();
        } else return instance;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public static User getUser() {
        return user;
    }

    // == Setters ==
    public void setWebElement(WebElement webElement) {
        ScenarioContext.webElement = webElement;
    }

    public void setCurrentPage(Page currentPage) {
        ScenarioContext.currentPage = currentPage;
        PageFactory.initElements(driver, currentPage);
    }

    public void setScenario(Scenario scenario) {
        ScenarioContext.scenario = scenario;
    }

    public void reset(){
        instance = null;
    }

}
