Feature: As an administrator I should be able to view all employees and search.
  Background:
    Given I'm on logged in to OrangeHRM as admin

  @hrm
  Scenario: View All Employees
    And I click on the Employee List
    Then I see employee list table

    @HRM
    Scenario: Edit an Employee
      And I click on the Employee List
      #Then I edit an employee

    @HRM
    Scenario: Editing without name change
      And I click on the Employee List
      #Then I edit an employee without changing name
      #Then I should see the edited changes

