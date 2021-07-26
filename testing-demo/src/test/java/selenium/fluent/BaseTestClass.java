package selenium.fluent;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.Util;

import java.util.concurrent.TimeUnit;

import static selenium.fluent.DriverFactory.getChromeDriver;
import static selenium.fluent.DriverFactory.getWebDriverWait;

public abstract class BaseTestClass {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void startUpBrowser() {
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().fullscreen();
        wait = getWebDriverWait();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Before
    public void goToHome() {
        driver.get(Util.getStaticFileUrlString("/website/HomePage.html"));
    }
}
