package selenium.plural;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Util;

public class WebDriverTables {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(Util.getStaticFileUrlString("/TableTest.html"));

        //WebElement outerTable = driver.findElement(By.tagName("table"));
        //WebElement innerTable = outerTable.findElement(By.tagName("table"));
        //WebElement row = innerTable.findElements(By.tagName("td")).get(1);
        //System.out.println(row.getText());
        WebElement row = driver
                //.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td"));
                .findElement(By.xpath("//tbody/tr/td[2]/table/tbody/tr[2]/td"));
        System.out.println(row.getText());

        driver.quit();
    }
}
