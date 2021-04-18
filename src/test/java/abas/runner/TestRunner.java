package abas.runner;

import abas.base.SeleniumDriver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "features" },
        glue="abas.steps",
        plugin = { "pretty", "json:target/cucumber-reports/report.json", "junit:target/cucumber-reports/Cucumber.xml" },
        tags={"@Execute"})
public class TestRunner extends AbstractTestNGCucumberTests
{
    @BeforeClass
    public static void setUp()
    {
        SeleniumDriver.getInstance();
    }

    @AfterClass
    public static void tearDown()
    {

    }
}
