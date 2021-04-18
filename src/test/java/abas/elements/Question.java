package abas.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Question extends BaseElement
{
    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Question(String questionLabel)
    {
        super("//p[text()='" + questionLabel + "']//ancestor::div[@data-testid='question']");
    }

    // ==================================================
    // GETTERS AND SETTERS
    // ==================================================

    public WebElement getTextInputField()
    {
        return webElement.findElement(By.xpath(".//input"));
    }

    public Select getSelectDropdown()
    {
        WebElement selectElement = webElement.findElement(By.xpath(".//select"));
        return new Select(selectElement);
    }

    public List<Select> getSelectDropdowns()
    {
        List<WebElement> webElements = webElement.findElements(By.xpath(".//select"));
        List<Select> selects = new ArrayList<>();
        webElements.forEach(element -> selects.add(new Select(element)));
        return selects;
    }

    public List<WebElement> getButtons()
    {
        return webElement.findElements(By.xpath(".//button"));
    }
}
