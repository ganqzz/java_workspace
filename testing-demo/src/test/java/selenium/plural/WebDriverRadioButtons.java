package selenium.plural;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Util;

public class WebDriverRadioButtons {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(Util.getStaticFileUrlString("/RadioButtonTest.html"));

        List<WebElement> radioButtons = driver.findElements(By.name("color"));
        radioButtons.get(1).click();

        for (WebElement radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                System.out.println(radioButton.getAttribute("value"));
            }
        }

        driver.quit();
    }
}
