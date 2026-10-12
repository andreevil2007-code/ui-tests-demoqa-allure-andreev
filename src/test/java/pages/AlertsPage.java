package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {
    private final By alertBtn = By.id("alertButton");
    private final By confirmBtn = By.id("confirmButton");
    private final By promptBtn = By.id("promtButton"); // опечатка в id - так на сайте
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Alerts")
    public AlertsPage open() {
        openPath("/alerts");
        el(alertBtn);
        return this;
    }

    @Step("Открыть простой alert и принять его")
    public String openAlertAndAccept() {
        click(alertBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    @Step("Открыть confirm и нажать Cancel (dismiss)")
    public void openConfirmAndDismiss() {
        click(confirmBtn);
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    @Step("Открыть confirm и нажать OK (accept)")
    public void openConfirmAndAccept() {
        click(confirmBtn);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    @Step("Открыть prompt, ввести текст '{text}' и принять")
    public void openPromptAndSend(String text) {
        click(promptBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    public String confirmText() {
        return el(confirmResult).getText();
    }

    public String promptText() {
        return el(promptResult).getText();
    }
}
