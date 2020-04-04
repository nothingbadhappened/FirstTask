package step_definitions;

import helpers.Log;
import helpers.PropertiesUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.SignInAction;
import modules.SignOutAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.Body;
import pageObjects.Header;
import users.User;

public class SignIn {

    private static WebDriver driver;
    private static User user = new User();

    //Generic Step used in all scenarios
    @Given("user navigates to website")
    public void userNavigatesToWebsite() throws Throwable {
        try {
            Log.info("STEP: Given user navigates to " + PropertiesUtil.getUrl() + " website");
            driver = Hooks.driver;
            driver.get(PropertiesUtil.getUrl());
            Thread.sleep(1000);
        } catch (Exception e) {
            Log.error(e);
        }

    }

    //Other Steps
    //SCENARIO 1
    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) throws Throwable {
        try {
            Log.info("STEP: And user is " + registrationStatus + " on the website");
            user.setRegistrationStatus(registrationStatus);
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @When("user signs in with valid username {string} and password {string}")
    public void userSignIn(String username, String password) throws Throwable {

        try {
            Log.info("STEP: When user signs in with valid username: " + username + " and password:" + password);
            user.setUserName(username);
            user.setPassword(password);

            PageFactory.initElements(driver, Header.class);
            PageFactory.initElements(driver, Body.AccountPage.class);

            SignInAction.Execute(driver, user);

        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) throws Throwable {

        try {
            Thread.sleep(1500);
            Log.info("STEP: Then user is redirected to " + text + " page");
            Assert.assertEquals(Header.pageHeading.getText(), text);
            Log.info("SCENARIO: Passed");
        } catch (AssertionError e) {
            Log.info("!!! SCENARIO: Failed !!! " + e.getMessage());
            Assert.fail();
        }

    }

    // SCENARIO 2
    @And("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        Log.info("STEP: And user is " + registrationStatus + " on the website");
        user.setRegistrationStatus(registrationStatus);
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalidUserSignIn(String username, String password) throws Throwable {

        try {
            Log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
            user.setUserName(username);
            user.setPassword(password);

            PageFactory.initElements(driver, Header.class);
            PageFactory.initElements(driver, Body.AccountPage.class);

            SignInAction.Execute(driver, user);
        } catch (Exception e) {
            Log.error(e);
        }

    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) throws Exception {
        try {
            Thread.sleep(1500);
            Log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
            Assert.assertEquals(Body.AccountPage.login_error.getText(), errorText);
        } catch (Exception e) {
            Log.error(e);
            Assert.fail();
        }
    }


    // SCENARIO 3
    @And("user is logged in")
    public void userLoggedIn() throws Throwable {
        try {
            Log.info("STEP: And user is logged in");
            Thread.sleep(1000);
            userSignIn("elchupakabra@mailinator.com", "Test1234!");
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @When("user clicks sign out button")
    public void userSignOut() {
        try {
            Log.info("STEP: When user clicks sign out button");
            Thread.sleep(1000);
            SignOutAction.execute(driver);
        } catch (Exception e) {
            Log.error(e);
            Assert.fail();
        }

    }

    @Then("user is logged out")
    public void verifyUserSignedOut() {
        try {
            Log.info("STEP: Then user is logged out");
            Thread.sleep(1000);
            Assert.assertEquals(driver.getCurrentUrl().toString(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
            Log.info("User is redirected to Sign In page");
        } catch (Exception e) {
            Log.error("SCENARIO: FAIL - user is not redirected to Sign In page", e);
        }

        if (Header.pageHeading.getText().equals("MY ACCOUNT")) {
            Log.info("SCENARIO: FAIL - My Account is still present");
            Assert.fail();
        }
    }

}
