@FullRun
Feature: Sign In with user fetched from Database
  Background:
    Given user navigates to website

  Scenario: Registered user login with valid credentials
    When user status is "registered"
    And user signs in
    Then user is redirected to "My account - My Store" page