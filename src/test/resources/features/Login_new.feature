Feature: Login
  Scenario: Registered user login
    Given user is registered on the website
    When user navigates to website
    And user enters valid username "username" and password "password"
    Then user is redirected to "My Account" page