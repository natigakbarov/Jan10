Feature: This feature is verifying all practice tasks
@DownloadRun
  Scenario: User should be able to download file
    Given user navigates to "http://demo.automationtesting.in/FileDownload.html"
    Then user successfuly download the file
@excelRun
  Scenario: creating user from exel file and validate DB
    Given user navigates to the page
    Then create the expanse from Excel
    Then validate data from expense page
  And validate data from DB

