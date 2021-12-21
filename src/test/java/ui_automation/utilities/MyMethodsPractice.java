package ui_automation.utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.pages.OhrmEmployeesPage;
import ui_automation.pages.PracticePage;
import ui_automation.step_definitions.PracticeSteps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyMethodsPractice {

    WebDriver driver;
    public MyMethodsPractice(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }
PracticePage practicePage = new PracticePage();

    OhrmEmployeesPage ohrmEmployeesPage = new OhrmEmployeesPage();

    public void hrm_navigate_and_login(){
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui-config.properties","yollhrm.url"));
        ohrmEmployeesPage.userNameInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.username"));
        ohrmEmployeesPage.passwordInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.password"));
        ohrmEmployeesPage.loginBtn.click();
    }

    @FindBy (xpath = "//b[.='PIM']")
    public WebElement pimMenu;

    @FindBy (id = "menu_pim_addEmployee")
    public WebElement addEmployeeMenu;

    @FindBy (id = "firstName")
    public WebElement firstName;

    @FindBy (id = "middleName")
    public WebElement middleName;

    @FindBy (id = "lastName")
    public WebElement lastName;

    @FindBy (id = "employeeId")
    public WebElement employeeId;

    @FindBy (id = "btnSave")
    public WebElement btnSave;

    public String getDataFromExel(String name, String path) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + path);
        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheetAt(0);
        for (int i = 0; i <= sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            try {
                String value;
                if (name.equals(sheet.getRow(0).getCell(i).getStringCellValue())) {
                    value = sheet.getRow(1).getCell(i).getStringCellValue();
                    return value;
                } else continue;
            } catch (Exception IllegalStateException) {
                double result;
                String value;

                if (name.equals(sheet.getRow(0).getCell(1).getStringCellValue())) {
                    result = sheet.getRow(1).getCell(i).getNumericCellValue();
                    value = String.valueOf(result);
                    return value;

                } else continue;
            }
        }

return null;
    }

    public void mealB_login(){
        practicePage.userName.sendKeys(ConfigurationReader.getProperty("ui-config.properties","mealb.username"));
        practicePage.password.sendKeys(ConfigurationReader.getProperty("ui-config.properties","mealb.password"));
        practicePage.loginButton.click();
    }

    public void click_menu(String menuTab){
        for (WebElement elem: practicePage.mainMenu){
             System.out.println(elem.getText());
            if (elem.getText().equals(menuTab)){
                elem.click();
                break;}}
    }

    public void add_expense_select_dropdown(String option){
        driver.findElement(By.xpath("//div[@class='btn-group open']//a[.='"+option+"']")).click();
    }

    public void expenseRelationship_select_dropdown(String type){
        driver.findElement(By.xpath("/div[@class='btn-group bootstrap-select form-control open']//a[.='"+type+"Other']")).click();
    }




}
