package abas.steps;

import abas.base.SeleniumDriver;
import abas.elements.AnotherOccupancyQuestion;
import abas.elements.DateOfBirthQuestion;
import abas.elements.Question;
import abas.utils.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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

        // get question elements
        Question titleQuestion = new Question("Title");
        titleQuestion.findElement();

        Question firstNameQuestion = new Question("First name");
        firstNameQuestion.findElement();

        Question lastNameQuestion = new Question("Last name");
        lastNameQuestion.findElement();

        DateOfBirthQuestion dateOfBirthQuestion = new DateOfBirthQuestion("What is your date of birth?");
        dateOfBirthQuestion.findElement();

        Question maritalStatusQuestion = new Question("What is your marital status?");
        maritalStatusQuestion.findElement();

        Question occupationQuestion = new Question("What is your occupation?");
        occupationQuestion.findElement();

        AnotherOccupancyQuestion anotherOccupationQuestion = new AnotherOccupancyQuestion("Do you have another occupation as well?");
        anotherOccupationQuestion.findElement();

        // answer questions
        titleQuestion.getSelectDropdown().selectByVisibleText(title);
        firstNameQuestion.getTextInputField().sendKeys(firstName);
        lastNameQuestion.getTextInputField().sendKeys(lastName);
        dateOfBirthQuestion.setDayOfBirth(dateOfBirth);
        maritalStatusQuestion.getSelectDropdown().selectByVisibleText(maritalStatus);
        occupationQuestion.getTextInputField().sendKeys(occupation);
        anotherOccupationQuestion.answerQuestion(AnotherOccupancyQuestion.Answer.findByValue(hasAnotherOccupation));
    }
}
