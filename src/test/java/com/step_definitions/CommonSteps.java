package com.step_definitions;

import com.actions.common.NavigateHome;
import com.actions.common.SignOutAction;
import com.helpers.util.UserFactory;
import com.hooks.Hooks;
import com.pageObjects.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.helpers.configuration.ConfigFileReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.actions.common.SignInAction;
import org.testng.Assert;
import com.pageObjects.Header;
import com.users.User;
import static com.hooks.Hooks.driver;

public class CommonSteps {

    // == Fields ==
    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    private User user = Hooks.context.getBean(User.class);
    private UserFactory userFactory = Hooks.context.getBean(UserFactory.class);
    private SignInAction signInAction = Hooks.context.getBean(SignInAction.class);
    private SignOutAction signOutAction = Hooks.context.getBean(SignOutAction.class);
    private Header header = Hooks.context.getBean(Header.class);
    private Body body = Hooks.context.getBean(Body.class);



    // == Generic Step used in all scenarios - Background ==
    @Given("user navigates to website")
    public void userNavigatesToWebsite() {
        try {
            log.info("STEP: Given user navigates to " + ConfigFileReader.getUrl() + " website");
            NavigateHome.navigateToHomePage();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @And("{string} user  is pulled from the Database")
    public void userIsPulledFromTheDatabase(String registrationStatus) {
        user.setRegistrationStatus(registrationStatus);

    }

    // == Other Steps ==
    // == SCENARIO 1 ==
    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        try {
            log.info("STEP: And user is " + registrationStatus + " on the website");
            user.setRegistrationStatus(registrationStatus);
            //toDO: static User user = getRegisteredUserFromDB();
        } catch (Exception | Error e) {
            log.error("Could not set user registration status! info: " + e.toString());
            Assert.fail("Could not set user registration status! info: " + e.toString());
        }
    }

    @When("user signs in with valid username {string} and password {string}")
    public void userSignIn(String username, String password) {

        try {
            log.info("STEP: When user signs in with valid username: " + username + " and password:" + password);
            user.setUserEmail(username);
            user.setUserPassword(password);
            //todo: fetched DB user.getUserName, user.getUserPassword & remove username/password param from feature file
            signInAction.Execute(user);
        } catch (Exception | Error e) {
            log.error("Could not set user credentials! info: " + e.toString());
            Assert.fail("Could not set user credentials! info: " + e.toString());
        }
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) throws Throwable {

        try {
            log.info("STEP: Then user is redirected to " + text + " page");
            Assert.assertEquals(header.getPageHeading().getText(), text);
            log.info("SCENARIO: Passed");
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed !!! ~~~\n" + e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    // == SCENARIO 2 ==
    @And("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        log.info("STEP: And user is " + registrationStatus + " on the website");
        user.setRegistrationStatus(registrationStatus);
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalidUserSignIn(String username, String password) {

        try {
            log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
            user.setUserEmail(username);
            user.setUserPassword(password);

            signInAction.Execute(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) {
        try {
            log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
            Assert.assertEquals(body.getLoginErrorField().getText(), errorText);
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed ~~~ " + e.getMessage());
            Assert.fail();
        }
    }

    // == SCENARIO 3 ==
    @And("user is logged in")
    public void userLoggedIn() throws Throwable {
        try {
            log.info("STEP: And user is logged in");
            userSignIn("elchupakabra@mailinator.com", "Test1234!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @When("user clicks sign out button")
    public void userSignOut() {
        try {
            log.info("STEP: When user clicks sign out button");
            signOutAction.execute(driver);
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail();
        }

    }

    @Then("user is logged out")
    public void verifyUserSignedOut() {
        try {
            log.info("STEP: Then user is logged out");
            Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
            log.info("User is redirected to Sign In page");
        } catch (AssertionError e) {
            log.info("~~~ SCENARIO: Failed ~~~ \nUser is not redirected to Sign In page - " + e.getMessage());
        }

        if (header.getPageHeading().getText().equals("MY ACCOUNT")) {
            log.info("~~~ SCENARIO: FAIL ~~~ \nMy Account is still present");
            Assert.fail();
        }
    }

    // == Spring JDBC test steps See SignInWithDatabasePulledUser.feature ==
    @When("user status is {string}")
    public void getRegisteredUserFromDatabase(String userRegistrationStatus){
        user = userFactory.getUser(userRegistrationStatus);
    }

    @And("user signs in")
    public void userSignsIn() throws Exception {
        signInAction.Execute(user);
    }

}
