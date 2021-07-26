package selenium.plural;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import selenium.Util;

public class WebDriverSelectItems {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(Util.getStaticFileUrlString("/SelectItemTest.html"));

        WebElement selectElement = driver.findElement(By.id("select1"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Maybe");

        driver.quit();
    }
}
