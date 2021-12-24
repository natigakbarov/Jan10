Feature: This feature file is verifying MealB expense functionality

  @runExpense
  Scenario: User should be able to create Meal and Entertainment expense from excel file
    Given user navigates to MealB landing page
    When user logs in with valid credentials
    Then user navigates to "Expenses" tab
    Then user navigates to "Meal and Entertainment" expense modal window
    Then user completes all fields on "Meal and Entertainment" expense modal window
    Then user clicks on "Save" button
    And user verifies created "Meal and Entertainment" expense on expenses table