Feature: Sign In
  Background:
    Given user navigates to website

  Scenario: Registered user login with valid credentials
    And user is "registered" on the website
    When user signs in with valid username "elchupakabra@mailinator.com" and password "Test1234!"
    Then user is redirected to "MY ACCOUaNT" page

  Scenario: Registered user login with invalid credentials
    And user is "not registered"
    When user enters invalid username "ratatui@ne.em" and password "badPassword"
    Then login error "Authentication failed." is displayed