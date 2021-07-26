package selenium.fluent.m6.pages.course;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static selenium.fluent.DriverFactory.getChromeDriver;

public class CourseVerifyController {

    private WebDriver driver = getChromeDriver();

    // Note: also exist in Common Verifier
    public CourseVerifyController freeTrialIsDisplayed() {
        assertTrue(driver.findElement(
                By.xpath("//div[@id='course-page-hero']//div[@class='ps-button section'][1]"))
                                .isDisplayed());
        return this;
    }

    public CourseVerifyController coursePreviewIsDisplayed() {
        assertTrue(driver.findElement(
                By.xpath("//div[@id='course-page-hero']//div[@class='ps-button section'][2]"))
                                .isDisplayed());
        return this;
    }
}
