package selenium.fluent.m6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static selenium.fluent.DriverFactory.getChromeDriver;

public abstract class AbstractPage {

    protected WebDriver driver = getChromeDriver();

    public AbstractPage verifyIsDisplayed(By element) {
        assertTrue(driver.findElement(element).isDisplayed());
        return this;
    }

    public AbstractPage verifyIsNotDisplayed(By element) {
        // your code ...
        return this;
    }
}
