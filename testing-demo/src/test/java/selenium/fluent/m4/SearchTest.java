package selenium.fluent.m4;

import org.junit.Test;
import selenium.fluent.BaseTestClass;
import selenium.fluent.m4.pages.CoursePage;
import selenium.fluent.m4.pages.HomePage;
import selenium.fluent.m4.pages.SearchPage;

public class SearchTest extends BaseTestClass {

    HomePage home = new HomePage();
    SearchPage search = new SearchPage();
    CoursePage course = new CoursePage();

    @Test
    public void basicFilterByTest() {
        home.search("Java");

        search.filterBySkillLevel("Beginner")
              .filterByRole("Software Development")
              .selectTabCourse()
              .selectCourse("Java Fundamentals: The Java Language");

        course.verifyCoursePreviewIsDisplayed()
              .verifyFreeTrialIsDisplayed();
    }
}
