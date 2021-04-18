package policyexpert.runner;

import policyexpert.base.SeleniumDriver;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "features" },
        glue="policyexpert.steps",
        plugin = { "pretty", "json:target/cucumber-reports/report.json", "html:test-output","junit:target/cucumber-reports/Cucumber.xml" },
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
