package com.endava.steps;

import com.endava.actions.common.AddToCart;
import com.endava.actions.common.SearchProduct;
import com.endava.actions.common.SignInAction;
import com.endava.actions.common.SignOutAction;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.CartPage;
import com.endava.pageObjects.Page;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.users.User;
import com.endava.users.UserProviderService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.testng.Assert;

import java.util.Map;

public class CommonSteps {

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    private User user;
    private WebElement element;
    private Page page;
    private ProductListItem productListItem;

    @Autowired
    private UserProviderService userProviderService;

    @Autowired
    private Environment environment;

    @Autowired
    private Browser browser;

    @Autowired
    private SignInAction signInAction;

    @Autowired
    private SignOutAction signOutAction;

    @Autowired
    private AddToCart addToCart;

    @Autowired
    private SearchProduct searchProduct;


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
        user = userProviderService.getUser(registrationStatus);
    }

    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        user = userProviderService.getUser(registrationStatus);
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
            e.printStackTrace();
        }
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) {
        page = StepContext.getCurrentPage();
        element = page.getElementByName("myAccountHeading");
        try {
            log.info("STEP: Then user is redirected to " + text + " page");
            Assert.assertEquals(browser.getPageTitle(), text);
            Assert.assertEquals(element.getText(), "MY ACCOUNT");
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed !!! ~~~\n" + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @And("user full name is displayed in the top navigation bar")
    public void userFullNameIsPresentInTheTopNavbar() {
        element = StepContext.getElement("userFullName");

        Assert.assertEquals(element.getText(), user.getUserFullName());
        log.info("STEP: Passed");
    }

    @When("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        log.info("STEP: And user is " + registrationStatus + " on the website");
        user = userProviderService.getUser(registrationStatus);
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

        signInAction.execute(user);

    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) {
        try {
            log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
//            Assert.assertEquals(PageFactory.getPageObject("body").getElementByName("loginErrorField").getText(), errorText);
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ " + e.getMessage());
            Assert.fail();
        }
    }

    @And("user is logged in")
    public void userLoggedIn() {
        try {
            log.info("STEP: And user is logged in");
            user = userProviderService.getUser("registered");
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
        page = StepContext.getCurrentPage();

        try {
            log.info("STEP: Then user is logged out");
            Assert.assertEquals(browser.getPageUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
            log.info("User is redirected to Sign In page");
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ \nUser is not redirected to Sign In page - " + e.getMessage());
        }

        if (browser.getPageTitle().equals("MY ACCOUNT")) {
            log.info("~~~ STEP: FAIL ~~~ \nMy Account is still present");
            Assert.fail();
        }

        try {
            page.getElementByName("userFullName");
            Assert.fail();
        } catch (InvalidArgumentException e) {
            log.info("~~~ STEP: PASSED ~~~");
        }
    }

    // == Spring JDBC test steps See SignInWithDatabasePulledUser.feature ==
    @When("user status is {string}")
    public void getRegisteredUserFromDatabase(String userRegistrationStatus) {
        user = userProviderService.getUser(userRegistrationStatus);
    }

    @And("user signs in")
    public void userSignsIn() {
        signInAction.execute(user);
    }

    @When("user navigates to {string} page")
    public void navigateToSummerDressesPage(String page) {
        String summerDressesURL = environment.getProperty("summerDressesURL");
        try {
            log.info("STEP: Given user navigates to " + summerDressesURL + " website");
            browser.goToUrl(summerDressesURL);
            //StepContext.setCurrentPage();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @And("user adds {string} to cart")
    public void userAddsItemToCart(String productName) {
        searchProduct.addToCart(productName);
    }

    @When("user searches for {string} item")
    public void userSearchesForItem(String productName) {
        log.info("Step: When user searches for " + productName + " item");
        StepContext.setProductNameElement(productName);
        searchProduct.execute(productName);
    }

    @Then("{string} is present in search results")
    public void productNameIsFound(String productName) {
        log.info("STEP: Then " + productName + " is present in search results");

        productListItem = StepContext.getProductListItem();

        try {
            Assert.assertTrue(searchProduct.getIsProductFound());
            Assert.assertEquals(productName, productListItem.getProductItemName().getText());
            log.info("~~~ STEP: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ \nThe product has not been found in search results - " + e.getMessage());
            Assert.fail(e.getStackTrace().toString());
        }
    }

    @Then("failed search message is displayed with text {string}")
    public void productNameIsNotFound(String expectedMessage) {
        page = StepContext.getCurrentPage();
        String actualMessage = page
                .getElementByName("failedSearchMessageElement")
                .getText()
                .replace("\"", "");

        try {
            log.info("Expecting search error message: " + expectedMessage);
            log.info("Received search error message: "+ actualMessage);
            Assert.assertEquals(actualMessage, expectedMessage);
            log.info("~~~ STEP: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ \nBad Failed Search Message - " + e.getMessage());
            Assert.fail();
        }
    }

    @And("user adds the found item to cart")
    public void userAddsFoundItemToCart() throws InterruptedException {
        addToCart.addSingleItem(StepContext.getProductListItem());
    }

    @And("user navigates to the cart page")
    public void userNavigatesToCartPage() {
        browser.getWebDriver().navigate().to("http://automationpractice.com/index.php?controller=order");
        StepContext.setCurrentPage(new CartPage(browser));
    }

    @Then("{string} page is loaded")
    public void shoppingCartSummaryPageIsLoaded(String pageHeadingName) {
        page = StepContext.getCurrentPage();
        try {
            Assert.assertTrue(page.getElementByName("cartTitleElement").getText().contains(pageHeadingName));
            log.info("~~~ STEP: PASSED ~~~");
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ \nBad Cart Page heading - " + e.getMessage());
            Assert.fail();
        }
    }

    @And("the item is present in the list")
    public void itemPresentInTheCart() {
        page = StepContext.getCurrentPage();
        try {
            //Assert.assertEquals(StepContext.getProductListItem().getProductItemName().getText(), StepContext.getProductNameElement());
            Assert.assertEquals(page.getElementByName("productItemName").getText(), StepContext.getProductNameElement());
        } catch (AssertionError e) {
            log.info("~~~ STEP: Failed ~~~ \nBad product name in the Cart - " + e.getMessage());
            Assert.fail();
        }
    }
}
