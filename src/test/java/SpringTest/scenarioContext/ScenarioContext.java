package springTest.scenarioContext;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import springTest.pageObjects.Page;
import springTest.users.User;
import static springTest.step_definitions.Hooks.driver;


public class ScenarioContext {
    private static ScenarioContext instance = null;

    private static Page currentPage;
    private static Scenario scenario;
    private static WebElement webElement;
    private static User user;

    private ScenarioContext() {
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            return new ScenarioContext();
        } else return instance;
    }


    public WebElement getWebElement() {
        return webElement;
    }

    public void setWebElement(WebElement webElement) {
        ScenarioContext.webElement = webElement;
    }

    public void setCurrentPage(Page currentPage) {
        ScenarioContext.currentPage = currentPage;
        PageFactory.initElements(driver, currentPage);
    }

    public Page getCurrentPage() {
        return currentPage;
    }


    public void setScenario(Scenario scenario) {
        ScenarioContext.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }
}
