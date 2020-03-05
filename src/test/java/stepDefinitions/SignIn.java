package stepDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import helpers.Log;
import helpers.PropertiesUtil;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modules.SignInAction;
import modules.SignOutAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.Body;
import pageObjects.Header;
import pageObjects.HomePage;
import pageObjects.LoginPage;


public class SignIn {

    private static WebDriver driver = Hooks.driver;
    private static List<HashMap<String,String>> dataMap = null;

    public SignIn() {
        dataMap = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> sampleData = new HashMap<String,String>();
        sampleData.put("username","elchupakabra@mailinator.com");
        sampleData.put("password","Test1234!");
        System.out.println("Current data" + sampleData);
        dataMap.add(sampleData);
    }

    @When("^I open automationpractice website$")
    public void user_navigatesTo_automationpractice_website() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(PropertiesUtil.getUrl());
//        Log.info("Step: " + Step.class.getSimpleName().toString());
        System.out.println(Step.class.getSimpleName().toString());
    }

    @When("^I sign in$")
    public void user_sign_in() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        PageFactory.initElements(driver, Header.class);
        PageFactory.initElements(driver, Body.LoginPage.class);

        SignInAction.Execute(driver, dataMap.get(0));

    }

    @Then("^I sign out$")
    public void user_sign_out() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        SignOutAction.Execute(driver);
    }

}