package policyexpert.elements;

import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class DateOfBirthQuestion extends Question
{
    // ==================================================
    // VARIABLES
    // ==================================================

    private Select daySelect;
    private Select monthSelect;
    private Select yearSelect;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public DateOfBirthQuestion(String questionLabel)
    {
        super(questionLabel);
    }

    // ==================================================
    // CLASS LOGIC
    // ==================================================

    @Override
    protected void onFindElement()
    {
        List<Select> selects = getSelectDropdowns();
        daySelect = selects.get(0);
        monthSelect = selects.get(1);
        yearSelect = selects.get(2);
    }

    public void setDayOfBirth(LocalDate dateOfBirth)
    {
        daySelect.selectByVisibleText(String.valueOf(dateOfBirth.getDayOfMonth()));
        monthSelect.selectByVisibleText(dateOfBirth.getMonth().getDisplayName(TextStyle.FULL, Locale.UK));
        yearSelect.selectByVisibleText(String.valueOf(dateOfBirth.getYear()));
    }
}
