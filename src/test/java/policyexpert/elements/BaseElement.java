package policyexpert.elements;

import policyexpert.base.SeleniumDriver;
import org.openqa.selenium.WebElement;

public class BaseElement
{
    // ==================================================
    // VARIABLES
    // ==================================================

    protected WebElement webElement;
    private final String xpath;

    protected final SeleniumDriver driver = SeleniumDriver.getInstance();

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public BaseElement(String xpath)
    {
        this.xpath = xpath;
    }

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    public void findElement()
    {
        webElement = driver.getWebElement(xpath);
        onFindElement();
    }

    protected void onFindElement()
    {

    }

    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public WebElement getWebElement()
    {
        return webElement;
    }
}
