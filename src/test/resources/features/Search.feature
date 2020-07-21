@FullRun
Feature: Search

  Scenario Outline: Single item search
    Given user navigates to website
    When user searches for "<productName>" item
    Then "<productName>" is present in search results

    Examples:
      | productName                 |
      | Printed Dress               |
      | Faded Short Sleeve T-shirts |

  Scenario Outline: There are no items returned by search
    Given user navigates to website
    When user searches for "<notExistingItem>" item
    Then failed search message is displayed with text "No results were found for your search <notExistingItem>"

    Examples:
      | notExistingItem   |
      | askfnaskfnpanfpan |
      | another example   |