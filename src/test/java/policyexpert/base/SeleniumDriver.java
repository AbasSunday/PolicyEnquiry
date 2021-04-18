package policyexpert.base;

import policyexpert.utils.DateUtils;
import policyexpert.utils.ElementState;
import policyexpert.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

public class SeleniumDriver implements WebDriver
{
    // ==================================================
    // VARIABLES
    // ==================================================

    private String osName;
    private String MAIN_URL;
    private int EXPLICIT_WAIT;

    private WebDriver driver;
    private Properties properties;

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    private void initialise()
    {
        loadConfig();
        printTestInfo();
        createDriver();
    }

    private void loadConfig()
    {
        try
        {
            // load config file
            properties = new Properties();
            properties.load(new FileInputStream("Config.properties"));

            // set values
            MAIN_URL = getProperty("MAIN_URL");
            EXPLICIT_WAIT = getIntProperty("EXPLICIT_WAIT");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void printTestInfo()
    {
        osName = Utils.getOperatingSystemName();

        Utils.logSeparator();
        Utils.log("| URL:              | " + MAIN_URL);
        Utils.log("| Java version      | " + Utils.getJavaVersion());
        Utils.log("| Operating system  | " + osName);
        Utils.log("| Time stamp        | " + DateUtils.getTimeStamp());
        Utils.logSeparator();
    }

    private void createDriver()
    {
        reduceChromeDriverLogging();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/" + getChromeDriverFileName());
        driver = new ChromeDriver(createChromeOptions());
    }

    /**
     * Reduces number of ChromeDriver log output to the console,
     * makes console cleaner and easier to see important output.
     * Use with caution, it might disable important logs in case of crash etc.
     */
    private void reduceChromeDriverLogging()
    {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
    }

    /**
     * Default ChromeOptions.
     * 1. There are number of arguments passed to chrome options, in order to
     *    prevent timeouts from happening, commented with links for reference.
     */
    private ChromeOptions createChromeOptions()
    {
        ChromeOptions options = new ChromeOptions();

        // options to prevent TIMEOUTS
        options.addArguments("start-maximized"); //https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        options.addArguments("--disable-features=VizDisplayCompositor"); //https://stackoverflow.com/questions/55373625/getting-timed-out-receiving-message-from-renderer-600-000-when-we-execute-selen
        options.addArguments("--disable-features=NetworkService");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-extensions");
        return options;
    }

    public void openWebsite()
    {
        driver.get(MAIN_URL);
    }

    /**
     * Draws red border around element, useful for debuging.
     * @param element that will have border around it
     */
    public void drawBorder(WebElement element)
    {
        try
        {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].style.border='3px solid red'", element);
        }
        catch (StaleElementReferenceException ignored)
        {

        }
    }

    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public String getProperty(String property)
    {
        return properties.getProperty(property);
    }

    public int getIntProperty(String property)
    {
        return Integer.parseInt(properties.getProperty(property));
    }

    public WebElement getWebElement(String xpath, ElementState elementState)
    {
        switch (elementState)
        {
            case IGNORE:
                return driver.findElement(By.xpath(xpath));
            case PRESENT:
                return driverWait(EXPLICIT_WAIT).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            case DISPLAYED:
                return driverWait(EXPLICIT_WAIT).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            case CLICKABLE:
                return driverWait(EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        }
        return null;
    }

    private WebDriverWait driverWait(long timeout)
    {
        return new WebDriverWait(driver, timeout);
    }

    private String getChromeDriverFileName()
    {
        switch (osName)
        {
            case "Windows 10": return "chromedriver_windows.exe";
            case "Mac OS X": return "chromedriver";
            default: throw new IllegalStateException("[" + osName + "] is not supported!");
        }
    }

    // ==================================================
    // SINGLETON HOLDER
    // ==================================================

    private static SeleniumDriver instance = null;

    private SeleniumDriver()
    {
        initialise();
    }

    public static SeleniumDriver getInstance()
    {
        if(instance == null)
        {
            instance = new SeleniumDriver();
        }
        return instance;
    }

    // ==================================================
    //  INTERFACE IMPLEMENTATION
    // ==================================================

    @Override
    public void get(String s)
    {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle()
    {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by)
    {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by)
    {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource()
    {
        return driver.getPageSource();
    }

    @Override
    public void close()
    {
        driver.close();
    }

    @Override
    public void quit()
    {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles()
    {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo()
    {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate()
    {
        return driver.navigate();
    }

    @Override
    public Options manage()
    {
        return driver.manage();
    }
}
