package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TextBoxPage;

@Epic("Elements")
@Feature("Text Box")
public class TextBoxTest extends BaseTest {

    @Test(description = "Позитив: валидные данные приводят к отображению блока результата")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Андреев Илья")
    public void shouldSubmitValidData() {
        TextBoxPage page = new TextBoxPage(driver, wait).open();
        page.fill("Ivan Petrov", "ivan.petrov@example.com", "Current", "Permanent");
        Assert.assertTrue(page.isOutputVisible(), "Ожидалось появление блока результатов.");
        Assert.assertTrue(page.outputText().contains("Ivan Petrov"),
                "В результате должно быть имя. Фактически: " + page.outputText());
    }

    @Test(description = "Негатив: невалидный email подсвечивается как ошибка")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldHighlightInvalidEmail() {
        TextBoxPage page = new TextBoxPage(driver, wait).open();
        page.fill("Ivan Petrov", "not-an-email", "Current", "Permanent");
        String cssClass = page.emailFieldClass();
        Assert.assertTrue(cssClass.contains("error") || cssClass.contains("invalid"),
                "Ожидалась индикация ошибки для поля email. Фактически class=" + cssClass);
               Assert.assertTrue(page.isOutputEmpty(), "При невалидном email в блоке результата не должно быть данных.");
    }
}
