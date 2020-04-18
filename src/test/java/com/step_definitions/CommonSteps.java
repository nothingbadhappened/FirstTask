package com.step_definitions;

import com.actions.common.NavigateHome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.helpers.configuration.ConfigFileReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.actions.common.SignInAction;
import org.testng.Assert;
import com.pageObjects.Header;
import com.users.User;
import static com.step_definitions.Hooks.driver;

@Component
public class CommonSteps {

    //Fields
    @Autowired
    static User user;

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    //Generic Step used in all scenarios - Background
    @Given("user navigates to website")
    public void userNavigatesToWebsite() {
        try {
            log.info("STEP: Given user navigates to " + ConfigFileReader.getUrl() + " website");
            NavigateHome.navigateToHomePage();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    //Other Steps
    //SCENARIO 1
    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        try {
            log.info("STEP: And user is " + registrationStatus + " on the website");
            user.setRegistrationStatus(registrationStatus);
            //toDO: static User user = getRegisteredUserFromDB();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @When("user signs in with valid username {string} and password {string}")
    public void userSignIn(String username, String password) {

        //todo: move the logic to SignIn action
        try {
            log.info("STEP: When user signs in with valid username: " + username + " and password:" + password);
            user.setUserEmail(username);
            user.setUserPassword(password);
            //todo: fetched DB user.getUserName, user.getUserPassword & remove username/password param from feature file
//            PageFactory.initElements(driver, Header.class);
//            PageFactory.initElements(driver, Body.AccountPage.class);

            SignInAction.Execute(user);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) throws Throwable {

        try {
            log.info("STEP: Then user is redirected to " + text + " page");
            Assert.assertEquals(Header.pageHeading.getText(), text);
            log.info("SCENARIO: Passed");
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed !!! ~~~\n" + e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    // SCENARIO 2
//    @And("user is {string}")
//    public void userIsNotRegistered(String registrationStatus) {
//        log.info("STEP: And user is " + registrationStatus + " on the website");
//        user.setRegistrationStatus(registrationStatus);
//    }
//
//    @When("user enters invalid username {string} and password {string}")
//    public void invalidUserSignIn(String username, String password) throws Throwable {
//
//        try {
//            log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
//            user.setUserEmail(username);
//            user.setUserPassword(password);
//
//            PageFactory.initElements(driver, Header.class);
//            PageFactory.initElements(driver, Body.class);
//
//            SignInAction.Execute(driver, user);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//
//    }
//
//    @Then("login error {string} is displayed")
//    public void loginError(String errorText) throws Exception {
//        try {
//            Thread.sleep(1500);
//            log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
//            Assert.assertEquals(Body.AccountPage.login_error.getText(), errorText);
//        } catch (AssertionError e) {
//            log.info("!!! SCENARIO: Failed !!! " + e.getMessage());
//            Assert.fail();
//        }
//    }
//
//
//    // SCENARIO 3
//    @And("user is logged in")
//    public void userLoggedIn() throws Throwable {
//        try {
//            log.info("STEP: And user is logged in");
//            userSignIn("elchupakabra@mailinator.com", "Test1234!");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    @When("user clicks sign out button")
//    public void userSignOut() {
//        try {
//            log.info("STEP: When user clicks sign out button");
//            SignOutAction.execute(driver);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            Assert.fail();
//        }
//
//    }
//
//    @Then("user is logged out")
//    public void verifyUserSignedOut() {
//        try {
//            log.info("STEP: Then user is logged out");
//            Assert.assertEquals(driver.getCurrentUrl().toString(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
//            log.info("User is redirected to Sign In page");
//        } catch (AssertionError e) {
//            log.info("!!! SCENARIO: Failed !!! User is not redirected to Sign In page - " + e.getMessage());
//        }
//
//        if (Header.pageHeading.getText().equals("MY ACCOUNT")) {
//            log.info("SCENARIO: FAIL - My Account is still present");
//            Assert.fail();
//        }
//    }

}
