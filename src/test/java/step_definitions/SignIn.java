package step_definitions;

import helpers.Log;
import helpers.PropertiesUtil;
import helpers.WebUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.SignInAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.Body;
import pageObjects.Header;
import users.User;

public class SignIn {

    private static WebDriver driver;
    private static User user = new User();

    //Generic Step for all scenarios in the feature
    @Given("user navigates to website")
    public void user_navigatesTo_website() throws Throwable {
        Log.info("STEP: Given user navigates to " + PropertiesUtil.getUrl() + " website");
        driver = Hooks.driver;
        driver.get(PropertiesUtil.getUrl());
        Thread.sleep(1500);

    }


    //Other Steps
    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) throws Throwable {
        Log.info("STEP: Given user is " + registrationStatus + " on the website");
        user.setRegistrationStatus(registrationStatus);
    }

    @When("user signs in with valid username {string} and password {string}")
    public void user_sign_in(String username, String password) throws Throwable {

        Log.info("STEP: And user signs in with valid username: " + username + " and password:" + password);
        user.setUserName(username);
        user.setPassword(password);

        PageFactory.initElements(driver, Header.class);
        PageFactory.initElements(driver, Body.LoginPage.class);

        SignInAction.Execute(driver, user);

    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) throws Throwable {
        Thread.sleep(1500);
        Log.info("STEP: Then user is redirected to \"MY ACCOUNT\" page");
        WebUtil.isElementTextCorrect(driver, Header.my_account, text);

        if(!WebUtil.isElementTextCorrect(driver, Header.my_account, text)){
            Log.info("ERROR: actual element text [" + Header.my_account.getText() + "] does not match with expected element text [" + text + "]");
            //TODO: implement custom exception to handle unmatched strings
            Exception e = new Exception();
            Log.error(e);

        }
    }

    @And("user is {string}")
    public void userIsNotRegistered(String registrationStatus) throws Throwable {
        Log.info("STEP: Given user is " + registrationStatus + " on the website");
        user.setRegistrationStatus(registrationStatus);
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalid_user_sign_in(String username, String password) throws Throwable {

        Log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
        user.setUserName(username);
        user.setPassword(password);

        PageFactory.initElements(driver, Header.class);
        PageFactory.initElements(driver, Body.LoginPage.class);

        SignInAction.Execute(driver, user);

    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) throws Exception {
        Thread.sleep(1500);
        Log.info("STEP: Then login error is displayed: " + errorText);

        if(!WebUtil.isElementTextCorrect(driver, Body.LoginPage.login_error, errorText)){
            Log.info("ERROR: actual element text [" + Body.LoginPage.login_error.getText() + "] does not match with expected element text [" + errorText + "]");
            //TODO: implement custom exception to handle unmatched strings
            Exception e = new Exception();
            Log.error(e);

        }


    }




}
