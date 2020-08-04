package com.endava.steps;

import com.endava.actions.common.AddToCart;
import com.endava.actions.common.SearchProduct;
import com.endava.actions.common.SignIn;
import com.endava.actions.common.SignOut;
import com.endava.helpers.util.actionsUtil.Assert;
import com.endava.helpers.util.browser.Browser;
import com.endava.pageObjects.CartPage;
import com.endava.pageObjects.LoginPage;
import com.endava.pageObjects.MyAccountPage;
import com.endava.pageObjects.SearchPage;
import com.endava.pageObjects.modules.ProductListItem;
import com.endava.steps.context.ContextKeys;
import com.endava.steps.context.StepContext;
import com.endava.users.User;
import com.endava.users.UserProviderService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Map;

public class CommonSteps {

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    @Autowired
    private UserProviderService userProviderService;

    @Autowired
    private Environment environment;

    @Autowired
    private Browser browser;

    @Autowired
    private SignIn signIn;

    @Autowired
    private SignOut signOut;

    @Autowired
    private AddToCart addToCart;

    @Autowired
    private SearchProduct searchProduct;

    @Autowired
    private StepContext context;


    @Given("user navigates to website")
    public void userNavigatesToWebsite() {
        String homePage = environment.getProperty("url");
        log.info("STEP: Given user navigates to " + homePage + " website");
        browser.goToUrl(homePage);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @And("{string} user  is pulled from the Database")
    public void userIsPulledFromTheDatabase(String registrationStatus) {
        context.setContext(ContextKeys.CURRENT_USER, userProviderService.getUser(registrationStatus));
        log.info("~~~ STEP: PASSED ~~~");
    }

    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        context.setContext(ContextKeys.CURRENT_USER, userProviderService.getUser(registrationStatus));
        context.setContext(ContextKeys.USER_REGISTERED, userProviderService.getUser(registrationStatus));
        log.info("STEP: And user is " + registrationStatus + " on the website");
        log.info("~~~ STEP: PASSED ~~~");
    }

    @When("specific user signs in with valid credentials:")
    public void userSignIn(DataTable userCredentials) {
        Map<String, String> data = userCredentials.asMap(String.class, String.class);
        data.forEach((k, v) -> log.debug("User Credentials datatable -> Key: " + k + " Value: " + v));
        User user = new User();

        log.info("STEP: When specific user signs in with valid credentials");
        user.setUserFullName(data.get("userFullName"));
        log.debug("Assigned userFullName to value: " + data.get("userFullName"));
        user.setUserEmail(data.get("userEmail"));
        log.debug("Assigned userEmail to value: " + data.get("userEmail"));
        user.setUserPassword(data.get("userPassword"));
        log.debug("Assigned userPassword to value: " + data.get("userPassword"));
        context.setContext(ContextKeys.CURRENT_USER, user);

        signIn.execute(user);

        log.info("~~~ STEP: PASSED ~~~");
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String text) {
        MyAccountPage myAccountPage = (MyAccountPage) context.getContext(ContextKeys.MY_ACCOUNT_PAGE);
        WebElement element = myAccountPage.getMyAccountHeading();

        log.info("STEP: Then user is redirected to " + text + " page");
        Assert.assertEquals(browser.getPageTitle(), text);
        Assert.assertEquals(element.getText(), "MY ACCOUNT");

    }

    @And("user full name is displayed in the top navigation bar")
    public void userFullNameIsPresentInTheTopNavbar() {
        MyAccountPage myAccountPage = (MyAccountPage) context.getContext(ContextKeys.MY_ACCOUNT_PAGE);
        WebElement element = myAccountPage.getHeader().getUserFullName();
        User user = (User) context.getContext(ContextKeys.CURRENT_USER);
        Assert.assertEquals(element.getText(), user.getUserFullName());
        log.info("STEP: Passed");
    }

    @When("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        log.info("STEP: And user is " + registrationStatus + " on the website");
        User user = userProviderService.getUser(registrationStatus);
        context.setContext(ContextKeys.CURRENT_USER, user);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalidUserSignIn(String username, String password) {
        User user = new User();

        log.info("STEP: When user enters invalid username: " + username + " and password:" + password);
        user.setUserEmail(username);
        user.setUserPassword(password);

        signIn.execute(user);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) {
        log.info("STEP: Then login error is displayed: [  " + errorText + " ]");
        LoginPage loginPage = (LoginPage) context.getContext(ContextKeys.LOGIN_PAGE);
        Assert.assertEquals(loginPage.getElementByName("loginErrorField").getText(), errorText);
    }

    @And("user is logged in")
    public void userLoggedIn() {
        log.info("STEP: And user is logged in");
        User user = userProviderService.getUser("registered");
        context.setContext(ContextKeys.CURRENT_USER, user);
        context.setContext(ContextKeys.USER_REGISTERED, user);
        signIn.execute(user);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @When("user clicks sign out button")
    public void userSignOut() {
        log.info("STEP: When user clicks sign out button");
        signOut.execute();
        log.info("~~~ STEP: PASSED ~~~");
    }

    @Then("user is logged out")
    public void verifyUserSignedOut() {
        LoginPage loginPage = (LoginPage) context.getContext(ContextKeys.CURRENT_PAGE);

        log.info("STEP: Then user is logged out");
        Assert.assertEquals(browser.getPageUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");

        Assert.assertTrue(!browser.getPageTitle().equals("MY ACCOUNT"));
        log.info("User is redirected to Sign In page");

        Assert.assertNoSuchElement(loginPage.getHeader().getUserFullName());
    }

    @When("user status is {string}")
    public void getRegisteredUserFromDatabase(String userRegistrationStatus) {
        User user = userProviderService.getUser(userRegistrationStatus);
        context.setContext(ContextKeys.CURRENT_USER, user);
        context.setContext(ContextKeys.USER_REGISTERED, user);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @And("user signs in")
    public void userSignsIn() {
        signIn.execute((User) context.getContext(ContextKeys.USER_REGISTERED));
        log.info("~~~ STEP: PASSED ~~~");
    }

    @And("user adds {string} to cart")
    public void userAddsItemToCart(String productName) {
        searchProduct.addToCart(productName);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @When("user searches for {string} item")
    public void userSearchesForItem(String productName) {
        log.info("Step: When user searches for " + productName + " item");
        searchProduct.execute(productName);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @Then("{string} is present in search results")
    public void productNameIsFound(String productName) {
        log.info("STEP: Then " + productName + " is present in search results");
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        Assert.assertTrue(searchProduct.isProductFound());
        WebElement element = searchPage.getProductListItem().getProductItemNameElement();
        Assert.assertEquals(productName, element.getText());
    }

    @Then("failed search message is displayed with text {string}")
    public void productNameIsNotFound(String expectedMessage) {
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        browser.waitUntilElementIsVisible(searchPage.getElementByName("failedSearchMessageElement"));
        String actualMessage = searchPage
                .getElementByName("failedSearchMessageElement")
                .getText()
                .replace("\"", "");

        Assert.assertEquals(actualMessage, expectedMessage);

    }

    @And("user adds the found item to cart")
    public void userAddsFoundItemToCart() throws InterruptedException {
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        ProductListItem productListItem = searchPage.getProductListItem();
        addToCart.addSingleItemFromSearchPage(productListItem);
        log.info("~~~ STEP: PASSED ~~~");
    }

    @And("user navigates to the cart page")
    public void userNavigatesToCartPage() {
        browser.getWebDriver().navigate().to("http://automationpractice.com/index.php?controller=order");
        context.setContext(ContextKeys.CART_PAGE, new CartPage(browser));
        log.info("~~~ STEP: PASSED ~~~");
    }

    @Then("{string} page is loaded")
    public void shoppingCartSummaryPageIsLoaded(String pageHeadingName) {
        CartPage cartPage = (CartPage) context.getContext(ContextKeys.CART_PAGE);
        Assert.assertTrue(cartPage.getElementByName("cartTitleElement").getText().contains(pageHeadingName));
    }

    @And("the {string} item is present in the list")
    public void itemPresentInTheCart(String productName) {
        CartPage cartPage = (CartPage) context.getContext(ContextKeys.CART_PAGE);
        Assert.assertEquals(cartPage.getElementByName("productItemName").getText(), productName);
    }
}
