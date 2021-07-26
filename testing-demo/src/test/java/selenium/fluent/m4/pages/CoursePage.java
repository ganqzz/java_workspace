package selenium.fluent.m4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static selenium.fluent.DriverFactory.getChromeDriver;

public class CoursePage {

    private WebDriver driver = getChromeDriver();

    public CoursePage verifyFreeTrialIsDisplayed() {
        assertTrue(driver.findElement(
                By.xpath("//div[@id='course-page-hero']//div[@class='ps-button section'][1]"))
                                .isDisplayed());
        return this;
    }

    public CoursePage verifyCoursePreviewIsDisplayed() {
        assertTrue(driver.findElement(
                By.xpath("//div[@id='course-page-hero']//div[@class='ps-button section'][2]"))
                                .isDisplayed());
        return this;
    }
}
