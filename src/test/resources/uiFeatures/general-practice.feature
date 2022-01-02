Feature: This feature file tests iFrame Page functionality

  @runiFrame @parallel
  Scenario: User should be able  verify frame on Frame Page
    Given Navigate to "http://the-internet.herokuapp.com"
    When user clicks on Frames tab
    And  user clicks on Nested Frames tab
    And user switch to buttom frame->verify and print text from buttom frame on console
    And user switch back to main HTML
    And user switch to top outer frame;
    Then user switch to inner frames -> verify  and print text from  frames on user console

  @DownloadRun @parallel
  Scenario: User should be able to download file
    Given user navigates to download page
    Then user successfully downloads the file

  @hrm
  Scenario: View All Employees
    Given I'm on logged in to OrangeHRM as admin
    And I click on the Employee List
    Then I see employee list table