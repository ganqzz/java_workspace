package selenium.fluent.m6.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static selenium.fluent.DriverFactory.getChromeDriver;

public class LoginPage {

    private LoginActController act;
    private LoginVerifyController verify;

    public LoginActController act() {
        return act;
    }

    public LoginVerifyController verify() {
        return verify;
    }

    private WebDriver driver = getChromeDriver();

    private LoginPage() {
        // hide it
    }

    private LoginPage(LoginActController act, LoginVerifyController verify) {
        this.act = act;
        this.verify = verify;
    }

    public static LoginPage get() {
        return new LoginPage(new LoginActController(),
                             new LoginVerifyController());
    }

    public static By invalidPasswordAndEmail() {
        return By.id("sign_in_fail");
    }
}
