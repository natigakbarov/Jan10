package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.IframePage;
import ui_automation.utilities.Driver;
import java.util.concurrent.TimeUnit;

public class IframeSteps  {
    IframePage iframepage=new IframePage();
    String expectedHeadTitle;
    String actualHeadTitle;

    @Given("Navigate to {string}")
    public void navigate_to(String url) {
        Driver.getInstance().getDriver().get(url);
        Driver.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("user clicks on Frames tab")
    public void user_clicks_on_Frames_tab() {
        iframepage.frame.click();
    }

    @When("user clicks on Nested Frames tab")
    public void user_clicks_on_Nested_Frames_tab() {
        iframepage.nestedframe.click();
    }

    @When("user switch to buttom frame->verify and print text from buttom frame on console")
    public void user_switch_to_buttom_frame_verify_and_print_text_from_buttom_frame_on_console() {
        expectedHeadTitle="BOTTOM";
        Driver.getInstance().getDriver().switchTo().frame("frame-bottom");
        actualHeadTitle=iframepage.getBodyText.getText();
        Assert.assertEquals("Button Outer frame verification is failed",expectedHeadTitle,actualHeadTitle);
        System.out.println(actualHeadTitle);
    }

    @When("user switch back to main HTML")
    public void user_switch_back_to_main_HTML() {
        Driver.getInstance().getDriver().switchTo().defaultContent();
    }

    @When("user switch to top outer frame;")
    public void user_switch_to_top_outer_frame() {
        Driver.getInstance().getDriver().switchTo().frame("frame-top");
    }

    @When("user switch to inner frames -> verify  and print text from  frames on user console")
    public void user_switch_to_inner_frames_verify_and_print_text_from_frames_on_user_console() throws InterruptedException {
        iframepage.allTopInnerFrames.stream().forEach(e->{
            Driver.getInstance().getDriver().switchTo().frame(e);
            Assert.assertNotNull("Upper Inner frame verification is failed",iframepage.getBodyText.getText());
            System.out.println(iframepage.getBodyText.getText());
            Driver.getInstance().getDriver().switchTo().parentFrame();

        });
        Thread.sleep(3000);
        Driver.getInstance().getDriver().close();
    }
}