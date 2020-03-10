Feature: Login
  Scenario: Registered user login with valid credentials
    Given user is "registered" on the website
    When user navigates to website
    And user signs in with valid username "elchupakabra@mailinator.com" and password "Test1234!"
    Then user is redirected to "My Account" page

#  Scenario: Registered user login with invalid credentials
#    Given user is "not registered" on the website
#    When user navigates to website
#    And user enters invalid username "ratatui@ne.em" and password "badPassword"
#    Then login error is displayed