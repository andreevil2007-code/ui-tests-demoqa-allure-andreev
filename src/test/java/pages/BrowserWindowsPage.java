package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserWindowsPage extends BasePage {
    private final By tabButton = By.id("tabButton");
    private final By heading = By.id("sampleHeading");

    public BrowserWindowsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Browser Windows")
    public BrowserWindowsPage open() {
        openPath("/browser-windows");
        el(tabButton);
        return this;
    }

    @Step("Открыть новую вкладку, прочитать заголовок, закрыть вкладку и вернуться")
    public String openNewTabReadHeadingAndClose() {
        String original = driver.getWindowHandle();
        click(tabButton);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(original)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String text = el(heading).getText();
        driver.close();
        driver.switchTo().window(original);
        return text;
    }

    public int windowsCount() {
        return driver.getWindowHandles().size();
    }
}
