package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SliderPage;

@Epic("Widgets")
@Feature("Slider")
public class SliderTest extends BaseTest {

    @Test(description = "Слайдер меняет значение стрелками клавиатуры")
    @Severity(SeverityLevel.MINOR)
    @Owner("Андреев Илья")
    public void shouldChangeValueWithArrows() {
        SliderPage page = new SliderPage(driver, wait).open();
        Assert.assertEquals(page.value(), "25", "Начальное значение слайдера");
        page.moveRight(5);
        Assert.assertEquals(page.value(), "30", "Значение после 5 нажатий вправо");
        page.moveLeft(10);
        Assert.assertEquals(page.value(), "20", "Значение после 10 нажатий влево");
    }
}
