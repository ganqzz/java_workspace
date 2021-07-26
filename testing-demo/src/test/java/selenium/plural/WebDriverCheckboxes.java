package selenium.plural;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Util;

public class WebDriverCheckboxes {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(Util.getStaticFileUrlString("/CheckboxTest.html"));

        WebElement checkbox = driver.findElement(By.id("lettuceCheckbox"));

        checkbox.click();
        checkbox.click();
        checkbox.click();

        driver.quit();
    }
}
