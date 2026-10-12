package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AllureAttachments;
import utils.Config;
import utils.DriverFactory;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Браузер берётся из системного свойства -Dbrowser (если задано),
     * иначе из параметра testng.xml, иначе chrome.
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("false") String headless) {
        String chosenBrowser = System.getProperty("browser", browser);
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", headless));

        driver = DriverFactory.create(chosenBrowser, isHeadless);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Config.TIMEOUT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null && !result.isSuccess()) {
            try {
                AllureAttachments.screenshot(driver, "Failure screenshot");
                AllureAttachments.pageSource(driver, "Page source on failure");
            } catch (Exception ignored) {
                // например, открыт alert - вложения не критичны
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
