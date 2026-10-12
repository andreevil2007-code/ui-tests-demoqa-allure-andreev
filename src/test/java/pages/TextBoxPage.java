package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBoxPage extends BasePage {
    private final By userName = By.id("userName");
    private final By userEmail = By.id("userEmail");
    private final By currentAddress = By.id("currentAddress");
    private final By permanentAddress = By.id("permanentAddress");
    private final By submit = By.id("submit");
    private final By output = By.id("output");

    public TextBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Text Box")
    public TextBoxPage open() {
        openPath("/text-box");
        el(userName);
        return this;
    }

    @Step("Заполнить форму (name={name}, email={email}) и нажать Submit")
    public void fill(String name, String email, String curr, String perm) {
        type(userName, name);
        type(userEmail, email);
        type(currentAddress, curr);
        type(permanentAddress, perm);
        click(submit);
    }

    public boolean isOutputVisible() {
        return el(output).isDisplayed();
    }

    public boolean isOutputAbsent() {
        return driver.findElements(output).isEmpty();
    }

    public String outputText() {
        return el(output).getText();
    }

    public String emailFieldClass() {
        return driver.findElement(userEmail).getAttribute("class");
    }
}
