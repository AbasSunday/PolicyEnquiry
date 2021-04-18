package policyexpert.elements.section;

import policyexpert.elements.questions.AnotherOccupancyQuestion;
import policyexpert.elements.questions.DateOfBirthQuestion;
import policyexpert.elements.questions.Question;

public class AboutYourselfSection
{
    // ==================================================
    // VARIABLES
    // ==================================================

    private Question titleQuestion;
    private Question firstNameQuestion;
    private Question lastNameQuestion;
    private DateOfBirthQuestion dateOfBirthQuestion;
    private Question maritalStatusQuestion;
    private Question occupationQuestion;
    private AnotherOccupancyQuestion anotherOccupationQuestion;
    private Question mainPhoneNumberQuestion;
    private Question emailAddressQuestion;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public AboutYourselfSection()
    {
        findElements();
    }

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    private void findElements()
    {
        // get question elements
        titleQuestion = new Question("Title");
        titleQuestion.findElement();

        firstNameQuestion = new Question("First name");
        firstNameQuestion.findElement();

        lastNameQuestion = new Question("Last name");
        lastNameQuestion.findElement();

        dateOfBirthQuestion = new DateOfBirthQuestion("What is your date of birth?");
        dateOfBirthQuestion.findElement();

        maritalStatusQuestion = new Question("What is your marital status?");
        maritalStatusQuestion.findElement();

        occupationQuestion = new Question("What is your occupation?");
        occupationQuestion.findElement();

        anotherOccupationQuestion = new AnotherOccupancyQuestion("Do you have another occupation as well?");
        anotherOccupationQuestion.findElement();

        mainPhoneNumberQuestion = new Question("Main phone number");
        mainPhoneNumberQuestion.findElement();

        emailAddressQuestion = new Question("What is your e-mail address?");
        emailAddressQuestion.findElement();
    }

    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public Question getTitleQuestion()
    {
        return titleQuestion;
    }

    public Question getFirstNameQuestion()
    {
        return firstNameQuestion;
    }

    public Question getLastNameQuestion()
    {
        return lastNameQuestion;
    }

    public DateOfBirthQuestion getDateOfBirthQuestion()
    {
        return dateOfBirthQuestion;
    }

    public Question getMaritalStatusQuestion()
    {
        return maritalStatusQuestion;
    }

    public Question getOccupationQuestion()
    {
        return occupationQuestion;
    }

    public AnotherOccupancyQuestion getAnotherOccupationQuestion()
    {
        return anotherOccupationQuestion;
    }

    public Question getMainPhoneNumberQuestion()
    {
        return mainPhoneNumberQuestion;
    }

    public Question getEmailAddressQuestion()
    {
        return emailAddressQuestion;
    }
}
