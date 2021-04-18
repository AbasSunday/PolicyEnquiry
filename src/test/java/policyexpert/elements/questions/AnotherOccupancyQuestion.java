package policyexpert.elements.questions;

import org.openqa.selenium.WebElement;
import policyexpert.utils.ElementState;

import java.util.Arrays;
import java.util.List;

public class AnotherOccupancyQuestion extends Question
{
    // ==================================================
    // VARIABLES
    // ==================================================

    private WebElement yesButton;
    private WebElement noButton;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public AnotherOccupancyQuestion(String questionLabel)
    {
        super(questionLabel);
    }

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    @Override
    protected void onFindElement()
    {
        List<WebElement> buttons = getButtons();
        yesButton = buttons.get(0);
        noButton = buttons.get(1);
    }

    public void answerQuestion(Answer answer, String anotherOccupationName)
    {
        switch (answer)
        {
            case YES:
                yesButton.click();
                findAnotherOccupancyNameQuestion(true).getTextInputField().sendKeys(anotherOccupationName);
                break;
            case NO:
                noButton.click();
                break;
        }
    }

    public Question findAnotherOccupancyNameQuestion(boolean findElement)
    {
        Question question = new Question("What is your other occupation?");

        if (findElement)
        {
            question.findElement(ElementState.PRESENT);
        }
        return question;
    }

    // ==================================================
    // INNER CLASSES
    // ==================================================

    public enum Answer
    {
        YES("Yes"),
        NO("No");

        private final String answer;

        Answer(String answer)
        {
            this.answer = answer;
        }

        public static Answer findByValue(final String answerValue)
        {
            Answer answer = Arrays.stream(values()).filter(value -> value.answer.equals(answerValue)).findFirst().orElse(null);

            if (answer == null)
            {
                throw new IllegalArgumentException("No enum found for [" + answerValue + "] value");
            }
            return answer;
        }
    }
}
