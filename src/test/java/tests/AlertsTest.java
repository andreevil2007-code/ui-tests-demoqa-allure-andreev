package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Alerts")
public class AlertsTest extends BaseTest {

    @Test(description = "Простой alert: текст окна корректен")
    @Severity(SeverityLevel.MINOR)
    @Owner("Андреев Илья")
    public void shouldShowSimpleAlert() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        String text = page.openAlertAndAccept();
        Assert.assertEquals(text, "You clicked a button", "Текст простого alert");
    }

    @Test(description = "Confirm: accept выводит 'Ok'")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldAcceptConfirm() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openConfirmAndAccept();
        Assert.assertTrue(page.confirmText().contains("Ok"),
                "Ожидался текст о выборе Ok. Фактически: " + page.confirmText());
    }

    @Test(description = "Confirm: dismiss выводит 'Cancel'")
    @Severity(SeverityLevel.MINOR)
    @Owner("Андреев Илья")
    public void shouldDismissConfirm() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openConfirmAndDismiss();
        Assert.assertTrue(page.confirmText().toLowerCase().contains("cancel"),
                "Ожидался текст о выборе Cancel. Фактически: " + page.confirmText());
    }

    @Test(description = "Prompt: введённый текст отражается в результате")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldAcceptPromptWithText() {
        AlertsPage page = new AlertsPage(driver, wait).open();
        page.openPromptAndSend("demo");
        Assert.assertTrue(page.promptText().toLowerCase().contains("demo"),
                "Ожидалось отображение введённого текста. Фактически: " + page.promptText());
    }
}
