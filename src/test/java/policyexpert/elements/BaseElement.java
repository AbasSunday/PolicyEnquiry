package policyexpert.elements;

import org.openqa.selenium.By;
import policyexpert.base.SeleniumDriver;
import org.openqa.selenium.WebElement;
import policyexpert.utils.ElementState;

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
        findElement(ElementState.CLICKABLE);
    }

    public void findElement(ElementState elementState)
    {
        webElement = driver.getWebElement(xpath, elementState);
        onFindElement();
    }

    protected void onFindElement()
    {

    }

    public boolean exist()
    {
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public WebElement getWebElement()
    {
        return webElement;
    }

    /**
     * @return information about what type of locator was used and locator itself.
     */
    public String getFullLocatorInfo()
    {
        return "found by [" + xpath + "] xpath";
    }
}
