package selenium.fluent.m6.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static selenium.fluent.DriverFactory.getChromeDriver;

public class SearchGetController {

    WebDriver driver = getChromeDriver();

    public List<String> courses() {
        List<WebElement> courses = driver.findElements(
                By.xpath(
                        "//div[@id='search-results-category-target']//div[@class='search-result__title']"));

        assertTrue("List is empty, failed to collect courses", courses.size() != 0);

        return courses.stream()
                      .map(WebElement::getText)
                      .collect(toList());
    }
}
