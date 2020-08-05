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
        log.info("STEP: Given user navigates to " + homePage + " website [START]");
        browser.goToUrl(homePage);
        log.info("~~~ STEP: Given user navigates to website [COMPLETE] ~~~");
    }

    @And("{string} user  is pulled from the Database")
    public void userIsPulledFromTheDatabase(String registrationStatus) {
        log.info("~~~ STEP: And {} user  is pulled from the Database [START] ~~~", registrationStatus);
        context.setContext(ContextKeys.CURRENT_USER, userProviderService.getUser(registrationStatus));
        log.info("~~~ STEP: And {} user  is pulled from the Database [COMPLETE] ~~~", registrationStatus);
    }

    @And("user is {string} on the website")
    public void userIsRegistered(String registrationStatus) {
        log.info("~~~ STEP: And user is {} on the website [START] ~~~", registrationStatus);
        context.setContext(ContextKeys.CURRENT_USER, userProviderService.getUser(registrationStatus));
        log.info("STEP: And user is " + registrationStatus + " on the website");
        log.info("~~~ STEP: And user is {} on the website [COMPLETE] ~~~", registrationStatus);
    }

    @When("specific user signs in with valid credentials:")
    public void userSignIn(DataTable userCredentials) {
        log.info("~~~ STEP: When specific user signs in with valid credentials [START] ~~~");
        Map<String, String> data = userCredentials.asMap(String.class, String.class);
        data.forEach((k, v) -> log.debug("User Credentials datatable -> Key: " + k + " Value: " + v));
        User user = new User();

        user.setUserFullName(data.get("userFullName"));
        log.debug("Assigned userFullName to value: " + data.get("userFullName"));
        user.setUserEmail(data.get("userEmail"));
        log.debug("Assigned userEmail to value: " + data.get("userEmail"));
        user.setUserPassword(data.get("userPassword"));
        log.debug("Assigned userPassword to value: " + data.get("userPassword"));
        context.setContext(ContextKeys.CURRENT_USER, user);

        signIn.execute(user);
        log.info("~~~ STEP: When specific user signs in with valid credentials [COMPLETE] ~~~");
    }

    @Then("user is redirected to {string} page")
    public void myAccountPageLoaded(String pageName) {
        log.info("~~~ STEP: Then user is redirected to {} page [START] ~~~", pageName);
        MyAccountPage myAccountPage = (MyAccountPage) context.getContext(ContextKeys.MY_ACCOUNT_PAGE);
        WebElement element = myAccountPage.getMyAccountHeading();

        log.info("STEP: Then user is redirected to " + pageName + " page");
        Assert.assertEquals(browser.getPageTitle(), pageName);
        Assert.assertEquals(element.getText(), "MY ACCOUNT");
        log.info("~~~ STEP: Then user is redirected to {} page [COMPLETE] ~~~", pageName);
    }

    @And("user full name is displayed in the top navigation bar")
    public void userFullNameIsPresentInTheTopNavbar() {
        log.info("~~~ STEP: And user full name is displayed in the top navigation bar [START] ~~~");
        MyAccountPage myAccountPage = (MyAccountPage) context.getContext(ContextKeys.MY_ACCOUNT_PAGE);
        WebElement element = myAccountPage.getHeader().getUserFullName();
        User user = (User) context.getContext(ContextKeys.CURRENT_USER);
        Assert.assertEquals(element.getText(), user.getUserFullName());
        log.info("~~~ STEP: And user full name is displayed in the top navigation bar [COMPLETE] ~~~");
    }

    @When("user is {string}")
    public void userIsNotRegistered(String registrationStatus) {
        log.info("~~~ STEP: And user is " + registrationStatus + " on the website [START] ~~~");
        User user = userProviderService.getUser(registrationStatus);
        context.setContext(ContextKeys.CURRENT_USER, user);
        log.info("~~~ STEP: And user is " + registrationStatus + " on the website [COMPLETE] ~~~");
    }

    @When("user enters invalid username {string} and password {string}")
    public void invalidUserSignIn(String username, String password) {
        log.info("~~~ STEP: When user enters invalid username: {}  and password: {} [START] ~~~", username, password);
        User user = new User();
        user.setUserEmail(username);
        user.setUserPassword(password);

        signIn.execute(user);
        log.info("~~~ STEP: When user enters invalid username: {}  and password: {} [COMPLETE] ~~~", username, password);
    }

    @Then("login error {string} is displayed")
    public void loginError(String errorText) {
        log.info("~~~ STEP: Then login error is displayed: [{}] [START] ~~~", errorText);
        LoginPage loginPage = (LoginPage) context.getContext(ContextKeys.LOGIN_PAGE);
        Assert.assertEquals(loginPage.getElementByName("loginErrorField").getText(), errorText);
        log.info("~~~ STEP: Then login error is displayed: [{}] [COMPLETE] ~~~", errorText);
    }

    @And("user is logged in")
    public void userLoggedIn() {
        log.info("~~~ STEP: And user is logged in [START] ~~~");
        User user = userProviderService.getUser("registered");
        context.setContext(ContextKeys.CURRENT_USER, user);
        context.setContext(ContextKeys.USER_REGISTERED, user);
        signIn.execute(user);
        log.info("~~~ STEP: And user is logged in [COMPLETE] ~~~");
    }

    @When("user clicks sign out button")
    public void userSignOut() {
        log.info("~~~ STEP: When user clicks sign out button [START] ~~~");
        signOut.execute();
        log.info("~~~ STEP: When user clicks sign out button [COMPLETE] ~~~");
    }

    @Then("user is logged out")
    public void verifyUserSignedOut() {
        log.info("~~~ STEP: Then user is logged out [START] ~~~");
        LoginPage loginPage = (LoginPage) context.getContext(ContextKeys.CURRENT_PAGE);

        log.info("STEP: Then user is logged out");
        Assert.assertEquals(browser.getPageUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");

        Assert.assertTrue(!browser.getPageTitle().equals("MY ACCOUNT"));
        log.info("User is redirected to Sign In page");

        Assert.assertNoSuchElement(loginPage.getHeader().getUserFullName());
        log.info("~~~ STEP: Then user is logged out [COMPLETE] ~~~");
    }

    @When("user status is {string}")
    public void getRegisteredUserFromDatabase(String userRegistrationStatus) {
        log.info("~~~ STEP: When user status is {} [START] ~~~", userRegistrationStatus);
        User user = userProviderService.getUser(userRegistrationStatus);

        context.setContext(ContextKeys.CURRENT_USER, user);
        context.setContext(ContextKeys.USER_REGISTERED, user);
        log.info("~~~ STEP: When user status is {} [COMPLETE] ~~~", userRegistrationStatus);
    }

    @And("user signs in")
    public void userSignsIn() {
        log.info("~~~ STEP: And user signs in [START] ~~~");
        signIn.execute((User) context.getContext(ContextKeys.USER_REGISTERED));
        log.info("~~~ STEP: And user signs in [COMPLETE] ~~~");
    }

    @And("user adds {string} to cart")
    public void userAddsItemToCart(String productName) {
        log.info("~~~ STEP: And user adds {} to cart [START] ~~~", productName);
        searchProduct.addToCart(productName);
        log.info("~~~ STEP: And user adds {} to cart [COMPLETE] ~~~", productName);
    }

    @When("user searches for {string} item")
    public void userSearchesForItem(String productName) {
        log.info("~~~ STEP: When user searches for {} item [START] ~~~", productName);
        searchProduct.execute(productName);
        log.info("~~~ STEP: When user searches for {} item [COMPLETE] ~~~", productName);
    }

    @Then("{string} is present in search results")
    public void productNameIsFound(String productName) {
        log.info("~~~ STEP: Then {} is present in search results [START] ~~~", productName);
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        Assert.assertTrue(searchProduct.isProductFound());
        WebElement element = searchPage.getProductListItem().getProductItemNameElement();
        Assert.assertEquals(productName, element.getText());
        log.info("~~~ STEP: Then {} is present in search results [COMPLETE] ~~~", productName);
    }

    @Then("failed search message is displayed with text {string}")
    public void productNameIsNotFound(String expectedMessage) {
        log.info("~~~ STEP: Then failed search message is displayed with text {} [START]", expectedMessage);
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        browser.waitUntilElementIsVisible(searchPage.getElementByName("failedSearchMessageElement"));
        String actualMessage = searchPage
                .getElementByName("failedSearchMessageElement")
                .getText()
                .replace("\"", "");

        Assert.assertEquals(actualMessage, expectedMessage);
        log.info("~~~ STEP: Then failed search message is displayed with text {} [COMPLETE]", expectedMessage);
    }

    @And("user adds the found item to cart")
    public void userAddsFoundItemToCart() throws InterruptedException {
        log.info("~~~ STEP: And user adds the found item to cart [START] ~~~");
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        ProductListItem productListItem = searchPage.getProductListItem();
        addToCart.addSingleItemFromSearchPage(productListItem);
        log.info("~~~ STEP: And user adds the found item to cart [COMPLETE] ~~~");
    }

    @And("user navigates to the cart page")
    public void userNavigatesToCartPage() {
        log.info("~~~ STEP: And user navigates to the cart page [START] ~~~");
        SearchPage searchPage = (SearchPage) context.getContext(ContextKeys.SEARCH_PAGE);
        searchPage.getHeader().getHeaderCartItem();
        context.setContext(ContextKeys.CART_PAGE, new CartPage(browser));
        log.info("~~~ STEP: And user navigates to the cart page [COMPLETE] ~~~");
    }

    @Then("{string} page is loaded")
    public void shoppingCartSummaryPageIsLoaded(String pageHeadingName) {
        log.info("~~~ STEP: Then {} page is loaded [START] ~~~", pageHeadingName);
        CartPage cartPage = (CartPage) context.getContext(ContextKeys.CART_PAGE);
        Assert.assertTrue(cartPage.getElementByName("cartTitleElement").getText().contains(pageHeadingName));
        log.info("~~~ STEP: Then {} page is loaded [COMPLETE] ~~~", pageHeadingName);
    }

    @And("the {string} item is present in the list")
    public void itemPresentInTheCart(String productName) {
        log.info("~~~ STEP: And the {} item is present in the list [START] ~~~", productName);
        CartPage cartPage = (CartPage) context.getContext(ContextKeys.CART_PAGE);
        Assert.assertEquals(cartPage.getElementByName("productItemName").getText(), productName);
        log.info("~~~ STEP: And the {} item is present in the list [COMPLETE] ~~~", productName);
    }
}
