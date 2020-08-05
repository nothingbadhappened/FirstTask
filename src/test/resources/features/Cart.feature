@FullRun
Feature: Cart
  Background:
    Given user navigates to website

    Scenario: User adds item to Cart
      When user searches for "Printed Chiffon Dress" item
      And user adds the found item to cart
      And user navigates to the cart page
      Then "SHOPPING-CART SUMMARY" page is loaded
      And the "Printed Chiffon Dress" item is present in the list