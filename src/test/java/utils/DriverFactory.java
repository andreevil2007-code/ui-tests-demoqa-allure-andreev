package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver create(String browser, boolean headless) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                driver = new FirefoxDriver(fo);
            }
            case "edge" -> {
                EdgeOptions eo = new EdgeOptions();
                eo.addArguments("--disable-notifications");
                if (headless) eo.addArguments("--headless=new");
                driver = new EdgeDriver(eo);
            }
            default -> {
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--disable-notifications");
                if (headless) co.addArguments("--headless=new", "--window-size=1920,1080");
                driver = new ChromeDriver(co);
            }
        }
        // запоминаем версию браузера для блока Environment в Allure
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        EnvironmentListener.register(caps.getBrowserName(), caps.getBrowserVersion());
        return driver;
    }
}
