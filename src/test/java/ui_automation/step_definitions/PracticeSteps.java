package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ui_automation.pages.PracticePage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.MyMethodsPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PracticeSteps {

    WebDriver driver = Driver.getInstance().getDriver();
    PracticePage practicePage = new PracticePage();
    MyMethodsPractice methods = new MyMethodsPractice();
    static final Logger oLog = LogManager.getLogger(PracticeSteps.class);

    // download file

    @Given("user navigates to {string}")
    public void user_navigates_to(String url) {
        oLog.info("Navigating to the Page");
        driver.get(url);
    }

    @Then("user successfuly download the file")
    public void user_successfuly_download_the_file() throws InterruptedException {
        oLog.info("Clicking on botton");
        practicePage.downloadBtn.click();
        Thread.sleep(3000);
        oLog.info("successfuly clicked");

    }

    // create new employee with exel

    @Given("user navigates to the page")
    public void user_navigates_to_the_page() throws InterruptedException {
        driver.get(ConfigurationReader.getProperty("ui-config.properties", "mealb.url"));
        methods.mealB_login();
        methods.click_menu("Expenses");
        practicePage.addExpanseBtn.click();
        methods.add_expense_select_dropdown("Meal & Entertainment");


    }

    @Then("create the expanse from Excel")
    public void create_the_expanse_from_Excel() throws IOException {
        String path = "/src/test/resources/testData/testData.xlsx";
        practicePage.expenseDateTime.click();
        practicePage.select_today_date();
        practicePage.amount.sendKeys(methods.getDataFromExel("Amount", path));
        practicePage.name.sendKeys( methods.getDataFromExel("Expense name", path));
      //  methods.expenseRelationship_select_dropdown("Other");
        practicePage.businessPurpose.sendKeys( methods.getDataFromExel("Business purpose", path));
        practicePage.company.sendKeys( methods.getDataFromExel("Company", path));
        practicePage.projectName.sendKeys(methods.getDataFromExel("Project name", path));
practicePage.expenseSaveBtn.click();
    }


    @Then("validate data from expense page")
    public void validate_data_from_expense_page() {

    }

    @Then("validate data from DB")
    public void validate_data_from_DB() {

    }











}
