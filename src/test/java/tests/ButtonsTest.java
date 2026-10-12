package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ButtonsPage;

@Epic("Elements")
@Feature("Buttons")
public class ButtonsTest extends BaseTest {

    @Test(description = "Двойной клик показывает сообщение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldDoubleClick() {
        String message = new ButtonsPage(driver, wait).open().doubleClick();
        Assert.assertEquals(message, "You have done a double click", "Сообщение после двойного клика");
    }

    @Test(description = "Правый клик показывает сообщение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldRightClick() {
        String message = new ButtonsPage(driver, wait).open().rightClick();
        Assert.assertEquals(message, "You have done a right click", "Сообщение после правого клика");
    }

    @Test(description = "Обычный клик показывает сообщение")
    @Severity(SeverityLevel.MINOR)
    @Owner("Андреев Илья")
    public void shouldSimpleClick() {
        String message = new ButtonsPage(driver, wait).open().simpleClick();
        Assert.assertEquals(message, "You have done a dynamic click", "Сообщение после обычного клика");
    }
}
