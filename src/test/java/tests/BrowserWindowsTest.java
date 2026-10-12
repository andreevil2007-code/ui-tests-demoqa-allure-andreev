package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Browser Windows")
public class BrowserWindowsTest extends BaseTest {

    @Test(description = "Новая вкладка открывается, содержит ожидаемый заголовок и закрывается")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андреев Илья")
    public void shouldOpenNewTab() {
        BrowserWindowsPage page = new BrowserWindowsPage(driver, wait).open();
        String heading = page.openNewTabReadHeadingAndClose();
        Assert.assertEquals(heading, "This is a sample page", "Заголовок новой вкладки");
        Assert.assertEquals(page.windowsCount(), 1, "После закрытия вкладки должно остаться одно окно");
    }
}
