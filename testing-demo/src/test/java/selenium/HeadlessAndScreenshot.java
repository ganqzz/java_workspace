package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class HeadlessAndScreenshot {
    public static void main(String[] args) throws IOException {

        // Set the property for webdriver.chrome.driver to be the location (absolute path)
        // not defined, search from the PATH environment variable
        //System.setProperty("webdriver.chrome.driver", "/Full/Path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        // Create new instance of ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        // And now use this to visit Google
        driver.get("https://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form
        element.submit();

        // screenshot
        // temporary file until quit()
        File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path path = ss.toPath();
        Files.move(path, Paths.get("out/selenium_ss.png"), REPLACE_EXISTING);
        System.out.println(path);

        // Close the browser
        driver.quit();
    }
}
