package policyexpert.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import policyexpert.base.SeleniumDriver;
import policyexpert.elements.BaseElement;
import policyexpert.elements.questions.AnotherOccupancyQuestion;
import policyexpert.elements.section.AboutYourselfSection;
import policyexpert.utils.AssertUtils;

import java.time.LocalDate;
import java.util.List;

public class NewPolicySteps
{
    // ==================================================
    // VARIABLES
    // ==================================================

    private final SeleniumDriver driver = SeleniumDriver.getInstance();

    // ==================================================
    // STEPS DEFINITION
    // ==================================================

    @Given("I opened new policy enquiry page")
    public void iOpenedNewPolicyEnquiryPage()
    {
        driver.openWebsite();
    }


    @When("I fill about you section")
    public void iFillAboutYouSection(DataTable table)
    {
        fillAboutYourSelfSection(table);
    }

    @When("I say that I {string} another occupation")
    public void iSayThatI(String value)
    {
        AssertUtils.assertPossibilities(value, "Have", "Don't have");
        boolean hasOtherOccupancy = value.equals("Have");
        AnotherOccupancyQuestion.Answer answer = hasOtherOccupancy ? AnotherOccupancyQuestion.Answer.YES : AnotherOccupancyQuestion.Answer.NO;

        AboutYourselfSection aboutYourselfSection = new AboutYourselfSection();
        aboutYourselfSection.getAnotherOccupationQuestion().answerQuestion(answer, "Account Director");
    }

    @Then("Another occupation input should be {string}")
    public void anotherOccupationInputShouldBe(String expectedState)
    {
        AssertUtils.assertPossibilities(expectedState, "Present", "Not Present");
        boolean expectedPresent = expectedState.equals("Present");

        AboutYourselfSection aboutYourselfSection = new AboutYourselfSection();

        // get input to be tested
        BaseElement anotherOccupancyNameInput = aboutYourselfSection.getAnotherOccupationQuestion().findAnotherOccupancyNameQuestion(false);

        // assert element state
        AssertUtils.assertElementPresence(expectedPresent, anotherOccupancyNameInput);
    }

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    private void fillAboutYourSelfSection(DataTable table)
    {
        // load answers from table
        List<List<String>> data = table.asLists(String.class);
        String title = data.get(0).get(1);
        String firstName = data.get(1).get(1);
        String lastName = data.get(2).get(1);
        String[] splitDate = data.get(3).get(1).split("/");
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
        String maritalStatus = data.get(4).get(1);
        String occupation = data.get(5).get(1);
        String hasAnotherOccupation = data.get(6).get(1);
        String anotherOccupationName = data.get(7).get(1);
        String phoneNumber = data.get(8).get(1);
        String emailAddress = data.get(9).get(1);

        AboutYourselfSection aboutYourselfSection = new AboutYourselfSection();

        // answer questions
        aboutYourselfSection.getTitleQuestion().getSelectDropdown().selectByVisibleText(title);
        aboutYourselfSection.getFirstNameQuestion().getTextInputField().sendKeys(firstName);
        aboutYourselfSection.getLastNameQuestion().getTextInputField().sendKeys(lastName);
        aboutYourselfSection.getDateOfBirthQuestion().setDayOfBirth(dateOfBirth);
        aboutYourselfSection.getMaritalStatusQuestion().getSelectDropdown().selectByVisibleText(maritalStatus);
        aboutYourselfSection.getOccupationQuestion().getTextInputField().sendKeys(occupation);
        aboutYourselfSection.getAnotherOccupationQuestion().answerQuestion(AnotherOccupancyQuestion.Answer.findByValue(hasAnotherOccupation), anotherOccupationName);
        aboutYourselfSection.getMainPhoneNumberQuestion().getTextInputField().sendKeys(phoneNumber);
        aboutYourselfSection.getEmailAddressQuestion().getTextInputField().sendKeys(emailAddress);
    }
}
