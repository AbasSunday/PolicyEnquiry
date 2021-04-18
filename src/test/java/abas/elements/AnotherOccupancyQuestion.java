package abas.elements;

import org.openqa.selenium.WebElement;

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

    public void answerQuestion(Answer answer)
    {
        switch (answer)
        {
            case YES:
                yesButton.click();
                break;
            case NO:
                noButton.click();
                break;
        }
    }

    private WebElement findAnotherOccupancyNameInput()
    {
        Question occupationQuestion = new Question("What is your other occupation?");
        return occupationQuestion.getTextInputField();
    }

    // ==================================================
    // INNER CLASSES
    // ==================================================

    public enum Answer
    {
        YES("Yes"),
        NO("NO");

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
