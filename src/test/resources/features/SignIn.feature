@FullRun
Feature: Sign In
  Background:
    Given user navigates to website

  Scenario: Specific user logs in with valid credentials
    And user is "registered" on the website
    When specific user signs in with valid credentials:
    | Field            | Value                        |
    | userFullName     | El Chupakabra Bro            |
    | userEmail        | elchupakabra@mailinator.com  |
    | userPassword     | Test1234!                    |
    Then user is redirected to "My account - My Store" page
    And user full name is displayed in the top navigation bar

  Scenario: User login with invalid credentials
    And user is "not registered"
    When user enters invalid username "ratatui@ne.em" and password "badPassword"
    Then login error "Authentication failed." is displayed

  Scenario: Registered user sign out
    And user is logged in
    When user clicks sign out button
    Then user is logged out