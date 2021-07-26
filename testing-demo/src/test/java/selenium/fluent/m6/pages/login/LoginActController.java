package selenium.fluent.m6.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static selenium.fluent.DriverFactory.getChromeDriver;

public class LoginActController {

    private WebDriver driver = getChromeDriver();

    public LoginActController enterUsername(String value) {
        WebElement search = driver.findElement(By.id("Username"));
        search.sendKeys(value);
        search.sendKeys(Keys.ENTER);
        return this;
    }

    public LoginActController enterPassword(String value) {
        WebElement search = driver.findElement(By.id("Password"));
        search.sendKeys(value);
        search.sendKeys(Keys.ENTER);
        return this;
    }

    public void clickSignIn() {
        driver.findElement(By.id("login_on_login_page")).click();
    }
}
