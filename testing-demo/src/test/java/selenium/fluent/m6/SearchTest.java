package selenium.fluent.m6;

import org.junit.Test;
import selenium.fluent.BaseTestClass;
import selenium.fluent.m6.pages.CommonVerification;
import selenium.fluent.m6.pages.course.CoursePage;
import selenium.fluent.m6.pages.home.HomePage;
import selenium.fluent.m6.pages.login.LoginPage;
import selenium.fluent.m6.pages.search.Role;
import selenium.fluent.m6.pages.search.SearchPage;
import selenium.fluent.m6.pages.search.SkillLevel;
import selenium.fluent.m6.pages.search.Tab;

import static org.assertj.core.api.Assertions.assertThat;
import static selenium.fluent.m6.pages.CommonVerification.getCommonVerification;
import static selenium.fluent.m6.pages.course.CoursePage.coursePreviewButton;
import static selenium.fluent.m6.pages.course.CoursePage.freeTrialButton;
import static selenium.fluent.m6.pages.course.CoursePage.getCoursePage;
import static selenium.fluent.m6.pages.home.HomePage.getHomePage;
import static selenium.fluent.m6.pages.search.SearchPage.getSearchPage;

public class SearchTest extends BaseTestClass {

    HomePage home = getHomePage();
    SearchPage search = getSearchPage();
    CoursePage course = getCoursePage();
    LoginPage login = LoginPage.get();
    CommonVerification common = getCommonVerification();

    @Test
    public void basicFilterByTest() {
        home.act()
            .search("Java");

        search.act()
              .filterBySkillLevel(SkillLevel.BEGINNER)
              .filterByRole(Role.SOFTWARE_DEVELOPMENT)
              .selectTab(Tab.COURSES)
              .selectCourse("Java Fundamentals: The Java Language");

        course.verify()
              .coursePreviewIsDisplayed()
              .freeTrialIsDisplayed();

        // OR

        common.verifyIsDisplayed(freeTrialButton())
              .verifyIsDisplayed(coursePreviewButton());
    }

    @Test
    public void searchListCheck() {
        home.act()
            .search("Java");

        search.act()
              .filterBySkillLevel(SkillLevel.BEGINNER)
              .filterByRole(Role.SOFTWARE_DEVELOPMENT)
              .selectTab(Tab.COURSES);

        assertThat(search.get().courses())
                .hasSize(25)
                .containsOnlyOnce("Java Fundamentals: The Java Language")
                .doesNotContain("Python");
    }

    @Test
    public void loginFails() {
        home.act()
            .clickLogin();

        login.act()
             .enterUsername("hoge")
             .enterPassword("fuga")
             .clickSignIn();

        common.verifyIsDisplayed(LoginPage.invalidPasswordAndEmail());
    }
}
