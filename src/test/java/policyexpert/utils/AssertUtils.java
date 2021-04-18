package policyexpert.utils;

import org.junit.Assert;
import policyexpert.base.SeleniumDriver;
import policyexpert.elements.BaseElement;

import java.util.Arrays;

public class AssertUtils
{
    /**
     * Assert that specified string equals at least one possibility
     * @param toAssert string that will be checked if it equals at least one possibility
     * @param possibilities list of possible/allowed strings
     */
    public static void assertPossibilities(String toAssert, String... possibilities)
    {
        for (String possibility : possibilities)
        {
            if (toAssert.equalsIgnoreCase(possibility))
            {
                return;
            }
        }
        Assert.fail("Incorrect possibility specified in feature file [" + toAssert + "], allowed possibilities: " + Arrays.toString(possibilities));
    }

    public static void assertElementPresence(boolean expectedPresent, BaseElement element)
    {
        boolean exists = element.exist();

        if (expectedPresent)
        {
            Assert.assertTrue("WebElement " + element.getFullLocatorInfo() + " should exist", exists);
        }
        else
        {
            if (exists)
            {
                element.findElement();
                SeleniumDriver.getInstance().drawBorder(element.getWebElement());
                Assert.fail("WebElement " + element.getFullLocatorInfo() + " (highlighted with red border in the screenshot) should NOT exist");
            }
        }
    }
}
