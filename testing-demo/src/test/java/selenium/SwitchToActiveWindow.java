package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchToActiveWindow {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://formy-project.herokuapp.com/switch-window");

        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();

        String originalHandle = driver.getWindowHandle();  // 現在のタブハンドルを保存

        for (String handle1 : driver.getWindowHandles()) {
            driver.switchTo().window(handle1);  // 切り替える
        }

        driver.switchTo().window(originalHandle);  // 戻る

        driver.quit();
    }
}
