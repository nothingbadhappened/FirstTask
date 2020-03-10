package step_definitions;

//import helpers.Log;
import helpers.PropertiesUtil;
import helpers.WebUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.SignInAction_New;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.Body;
import pageObjects.Header;
import users.User;


public class SignIn {

    private static WebDriver driver = Hooks.driver;
    private static User user = new User();
//    Log log = new Log();
//    log.startTestCase("Sign In");

//    log.info("Step execution started: Given user is \"registered\" on the website");
    @Given("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) throws Throwable {
        user.setRegistrationStatus(registrationStatus);
    }

    //    log.info("Step execution started: When user navigates to website");
    @When("^user navigates to website$")
    public void user_navigatesTo_website() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(PropertiesUtil.getUrl());
    }

    //    log.info("Step execution started: And user signs in with valid username and password");
    @And("user signs in with valid username {string} and password {string}")
    public void user_sign_in(String username, String password) throws Throwable {

        user.setUserName(username);
        user.setPassword(password);

        PageFactory.initElements(driver, Header.class);
        PageFactory.initElements(driver, Body.LoginPage.class);

        SignInAction_New.Execute(driver, user);

    }

    //log.info("Step execution started: Then user is redirected to \"My Account\" page");
    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) throws Throwable {
        Thread.sleep(2000);
        WebUtil.isElementTextCorrect(driver, Header.my_account, text);
    }
}
