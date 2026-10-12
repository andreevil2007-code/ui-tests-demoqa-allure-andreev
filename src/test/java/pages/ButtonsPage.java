package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage extends BasePage {
    private final By doubleClickBtn = By.id("doubleClickBtn");
    private final By rightClickBtn = By.id("rightClickBtn");
    private final By dynamicClickBtn = By.xpath("//button[text()='Click Me']"); // id у кнопки динамический
    private final By doubleMsg = By.id("doubleClickMessage");
    private final By rightMsg = By.id("rightClickMessage");
    private final By dynamicMsg = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Buttons")
    public ButtonsPage open() {
        openPath("/buttons");
        el(doubleClickBtn);
        return this;
    }

    @Step("Двойной клик по кнопке")
    public String doubleClick() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn));
        scrollTo(button);
        new Actions(driver).doubleClick(button).perform();
        return el(doubleMsg).getText();
    }

    @Step("Правый клик по кнопке")
    public String rightClick() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn));
        scrollTo(button);
        new Actions(driver).contextClick(button).perform();
        return el(rightMsg).getText();
    }

    @Step("Обычный клик по кнопке Click Me")
    public String simpleClick() {
        click(dynamicClickBtn);
        return el(dynamicMsg).getText();
    }
}
