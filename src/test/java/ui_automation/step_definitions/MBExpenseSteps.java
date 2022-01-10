package ui_automation.step_definitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui_automation.pages.MBExpensePage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.ExcelUtility;
import ui_automation.utilities.WaitHelper;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MBExpenseSteps {

    MBExpensePage mbExpensePage = new MBExpensePage();
    static final Logger log = LogManager.getLogger(MBExpenseSteps.class);

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    Date todaysDate = new Date();
    String date, expenseNameFromExcel, businessPurpose, company, projectName;
    double amount;

    @Given("user navigates to MealB landing page")
    public void user_navigates_to_MealB_landing_page() {
        log.info("Navigating to the Downloads Page");
        String mbLoginUrl = ConfigurationReader.getProperty("ui-config.properties","mealb.login.url");
        Driver.getInstance().getDriver().get(mbLoginUrl);
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() throws Exception {
        log.info("Logging in to MealB with valid credentials");
        mbExpensePage.login();
    }

    @Then("user navigates to {string} tab")
    public void user_navigates_to_tab(String tab) throws Exception {
        log.info("Navigating to " + tab + " Tab");
        switch (tab) {
            case "Dashboard":
                //TODO implement this dashboard case when implemented
                break;
            case "Expenses":
                mbExpensePage.mbExpensesTab.click();

                //TODO resolve this temp placeholder for this implementation
                String excelPath = System.getProperty("user.dir") + "/src/test/resources/testData/Keywords.xlsx";
                ExcelUtility.setExcelFile(excelPath, "Sheet1");
                /* Extract data for expense from Excel File */
                date = formatter.format(todaysDate).toString();
                expenseNameFromExcel = ExcelUtility.getCellData(1, 0);
                amount = ExcelUtility.getCellDataAsDouble(1, 3);
                businessPurpose = ExcelUtility.getCellData(1, 4);
                company = ExcelUtility.getCellData(1, 5);
                projectName = ExcelUtility.getCellData(1, 6);


//                mbExpensePage.deleteExistingRecord(expenseNameFromExcel);
                break;
        }
    }

    @Then("user navigates to {string} expense modal window")
    public void user_navigates_to_expense_modal_window(String expenseType) throws InterruptedException {
        mbExpensePage.navigateToExpenseModal(expenseType);
    }

    @Then("user completes all fields on {string} expense modal window")
    public void user_completes_all_fields_on_expense_modal_window(String expenseType) throws Exception {
//        String excelPath = System.getProperty("user.dir") + "/src/test/resources/testData/Keywords.xlsx";
//        ExcelUtility.setExcelFile(excelPath, "Sheet1");

        /* Extract data for expense from Faker Class */
//        Faker faker = new Faker();
//        String businessPurpose = faker.harryPotter().character();

//        /* Extract data for expense from Excel File */
//        date = formatter.format(todaysDate).toString();
//        expenseNameFromExcel = ExcelUtility.getCellData(1, 0);
//        amount = ExcelUtility.getCellDataAsDouble(1, 3);
//        businessPurpose = ExcelUtility.getCellData(1, 4);
//        company = ExcelUtility.getCellData(1, 5);
//        projectName = ExcelUtility.getCellData(1, 6);

        mbExpensePage.completeMealEntExpenseModal(date, expenseNameFromExcel, amount, businessPurpose, company, projectName);
    }

    @Then("user clicks on {string} button")
    public void user_clicks_on_button(String button) throws InterruptedException {
        mbExpensePage.saveButton.click();
        Thread.sleep(5000);
//        WaitHelper.waitForVisibility(mbExpensePage.expenseTable, 10);
    }

    @Then("user verifies created {string} expense on expenses table")
    public void user_verifies_created_expense_on_expenses_table(String expenseType) {
        mbExpensePage.verifyNewExpense(expenseNameFromExcel);
    }

    @And("user compares UI values with DB values")
    public void userComparesUIValuesWithDBValues() throws ClassNotFoundException, SQLException, InterruptedException {
        Thread.sleep(2000);
        Driver.getInstance().getDriver().
                findElement(By.xpath("//th[contains(@aria-label,'Expense name')]")).click();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String dbURL = ConfigurationReader.getProperty("ui-config.properties", "mb.database.url");
        String dbUsername = ConfigurationReader.getProperty("ui-config.properties", "mb.database.username");
        String dbPassword = ConfigurationReader.getProperty("ui-config.properties", "mb.database.password");

        String query = "select Name from mealb.dbo.Expenses as expenses\n" +
                "join mealb.dbo.AbpUserAccounts as users\n" +
                "on users.UserId = expenses.CreatorUserId\n" +
                "WHERE users.UserName = 'Elnar' and expenses.DeletionTime is NULL\n" +
                "order by expenses.Name ASC";

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        String dataFromUI;
        String dataFromDB;
        int i = 0;

        while (resultSet.next()) {
            List<WebElement> expenseNamesLocators = Driver.getInstance().getDriver().findElements(
                    By.xpath("//*[@id='expenses-table']/tbody/tr/td[2]"));

            dataFromUI = expenseNamesLocators.get(i).getText();
            dataFromDB = resultSet.getString("Name");

            Assert.assertEquals(dataFromDB + " - Expense Name validation failed!",
                    dataFromDB, dataFromUI);
            i++;
        }

    }
}
