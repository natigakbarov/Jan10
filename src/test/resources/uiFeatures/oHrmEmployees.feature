Feature: As an administrator I should be able to view all employees and search.
  Background:
    Given I'm on logged in to OrangeHRM as admin

  @hrm
  Scenario: View All Employees
    And I click on the Employee List
    Then I see employee list table
    #test comment
#A new feature file has been created

  #Working on user story 001