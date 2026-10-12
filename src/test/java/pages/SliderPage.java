package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SliderPage extends BasePage {
    private final By slider = By.cssSelector("input[type='range']");
    private final By sliderValue = By.id("sliderValue");

    public SliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Slider")
    public SliderPage open() {
        openPath("/slider");
        el(slider);
        return this;
    }

    @Step("Нажать стрелку вправо {times} раз")
    public void moveRight(int times) {
        WebElement s = el(slider);
        for (int i = 0; i < times; i++) {
            s.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    @Step("Нажать стрелку влево {times} раз")
    public void moveLeft(int times) {
        WebElement s = el(slider);
        for (int i = 0; i < times; i++) {
            s.sendKeys(Keys.ARROW_LEFT);
        }
    }

    public String value() {
        return el(sliderValue).getAttribute("value");
    }
}
