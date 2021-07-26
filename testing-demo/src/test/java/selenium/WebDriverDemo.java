package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverDemo {

    public static void main(String[] args) throws MalformedURLException {
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        //WebDriver driver = newRemoteDriver();

        driver.get("https://www.google.com");

        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys("pluralsight");
        searchField.submit();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // wait for each elements
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("画像")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("画像")));  // explicit wait

        //WebElement imagesLink = driver.findElements(By.linkText("画像")).get(0);
        WebElement imagesLink = driver.findElement(
                By.cssSelector("a[href*='/search?q=pluralsight&source=lnms&tbm=isch']"));
        imagesLink.click();

        WebElement imageElement = driver.findElements(By.cssSelector("a[class='rg_l']")).get(1);
        WebElement imageLink = imageElement.findElements(By.tagName("img")).get(0);
        imageLink.click();

        driver.quit();  // close the browser and terminate the driver process
    }

    public static WebDriver newRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                                   new DesiredCapabilities("chrome", "", Platform.WINDOWS));
    }
}
