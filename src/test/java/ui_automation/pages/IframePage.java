package ui_automation.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class IframePage {
    static final Logger log = LogManager.getLogger(MBExpensePage.class);
    WebDriver driver;
    public IframePage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Frames")
    public WebElement frame;

    @FindBy(linkText = "Nested Frames")
    public WebElement nestedframe;

    @FindBy(xpath = "//body")
    public WebElement getBodyText;

    @FindBy(xpath = "//frameset[@name='frameset-middle']/frame")
    public List<WebElement> allTopInnerFrames;

}