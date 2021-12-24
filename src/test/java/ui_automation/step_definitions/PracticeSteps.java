package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ui_automation.pages.PracticePage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

public class PracticeSteps {

    PracticePage practicePage = new PracticePage();
    static final Logger oLog = LogManager.getLogger(PracticeSteps.class);

    @Given("user navigates to download page")
    public void user_navigates_to_download_page() {
        oLog.info("Navigating to the Downloads Page");
        Driver.getInstance().getDriver().get
                (ConfigurationReader.getProperty("ui-config.properties","download.url"));
    }

    @Then("user successfully downloads the file")
    public void user_successfully_downloads_the_file() throws InterruptedException {
        oLog.info("Clicking on download button");
        practicePage.downloadButton.click();
        Thread.sleep(3000);
        oLog.info("Successfully clicked on download button");
        System.out.println("Successfully clicked on download button");
    }
}
