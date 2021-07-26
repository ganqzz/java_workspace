package selenium.fluent.m6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static selenium.fluent.DriverFactory.getChromeDriver;

public class CommonVerification {

    protected WebDriver driver = getChromeDriver();

    private CommonVerification() {
        // hide it
    }

    public static CommonVerification getCommonVerification() {
        return new CommonVerification();
    }

    public CommonVerification verifyIsDisplayed(By element) {
        assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }

    public CommonVerification verifyIsNotDisplayed(By element) {
        // your code ...
        return this;
    }
}
