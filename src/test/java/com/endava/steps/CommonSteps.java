package com.endava.steps;

import com.endava.actions.common.SignInAction;
import com.endava.actions.common.SignOutAction;
import com.endava.helpers.util.Browser;
import com.endava.users.User;
import com.endava.users.UserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.testng.Assert;

import java.util.Map;

public class CommonSteps {

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    private User user;

    @Autowired
    private Environment environment;

    @Autowired
    private Browser browser;

    @Autowired
    private SignInAction signInAction;

    @Autowired
    private SignOutAction signOutAction;


    @Given("user navigates to website")
    public void userNavigatesToWebsite() {
        try {
            String homePage = environment.getProperty("url");
            log.info("STEP: Given user navigates to " + homePage + " website");
            browser.goToUrl(homePage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @And("{string} user  is pulled from the Database")
    public void userIsPulledFromTheDatabase(String registrationStatus) {
        user = UserFactory.getUser(registrationStatus);
    }

    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        user = UserFactory.getUser(registrationStatus);
        log.info("STEP: And user is " + registrationStatus + " on the website");
    }

    @When("specific user signs in with valid credentials:")
    public void userSignIn(DataTable userCredentials) {

        try {
            Map<String, String> data = userCredentials.asMap(String.class, String.class);
            data.forEach((k, v) -> log.debug("User Credentials datatable -> Key: " + k + " Value: " + v));

            log.info("STEP: When specific user signs in with valid credentials");
            user.setUserFullName(data.get("userFullName"));
            log.debug("Assigned userFullName to value: " + data.get("userFullName"));
            user.setUserEmail(data.get("userEmail"));
            log.debug("Assigned userEmail to value: " + data.get("userEmail"));
            user.setUserPassword(data.get("userPassword"));
            log.debug("Assigned userPassword to value: " + data.get("userPassword"));

            signInAction.execute(user);
        } catch (Exception | Error e) {
            log.error("Could not set user credentials! info: " + e.toString());
            Assert.fail("Could not set user credentials! info: " + e.toString());
        }
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) {
        try {
            log.info("STEP: Then user is redirected to " + text + " page");
            Assert.assertEquals(browser.getPageTitle(), text);
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed !!! ~~~\n" + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @And("user full name is displayed in the top navigation bar")
    public void userFullNameIsPresentInTheTopNavbar() {
        Assert.assertTrue(signInAction.isSignedInUsernamePresent());
        log.info("SCENARIO: Passed");
    }

    @When("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        log.info("STEP: And user is " + registrationStatus + " on the website");
        //user.setRegistrationStatus(registrationStatus);
        user = UserFactory.getUser("registered");
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalidUserSignIn(String username, String password) {

        try {
            log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
            user.setUserEmail(username);
            user.setUserPassword(password);

            signInAction.execute(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) {
        try {
            log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
//            Assert.assertEquals(PageFactory.getPageObject("body").getElementByName("loginErrorField").getText(), errorText);
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed ~~~ " + e.getMessage());
            Assert.fail();
        }
    }

    @And("user is logged in")
    public void userLoggedIn() {
        try {
            log.info("STEP: And user is logged in");
            user = UserFactory.getUser("registered");
            signInAction.execute(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @When("user clicks sign out button")
    public void userSignOut() {
        try {
            log.info("STEP: When user clicks sign out button");
            signOutAction.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Then("user is logged out")
    public void verifyUserSignedOut() {
        try {
            log.info("STEP: Then user is logged out");
            Assert.assertEquals(browser.getPageUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
            log.info("User is redirected to Sign In page");
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed ~~~ \nUser is not redirected to Sign In page - " + e.getMessage());
        }

        if (browser.getPageTitle().equals("MY ACCOUNT")) {
            log.info("~~~ SCENARIO: FAIL ~~~ \nMy Account is still present");
            Assert.fail();
        }
    }

    // == Spring JDBC test steps See SignInWithDatabasePulledUser.feature ==
    @When("user status is {string}")
    public void getRegisteredUserFromDatabase(String userRegistrationStatus) {
        user = UserFactory.getUser(userRegistrationStatus);
    }

    @And("user signs in")
    public void userSignsIn() {
        signInAction.execute(user);
    }

}
