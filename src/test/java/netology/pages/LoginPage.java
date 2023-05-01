package netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import netology.data.DataHelper;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void clearFields() {
        loginField.doubleClick().sendKeys(Keys.DELETE);
        passwordField.doubleClick().sendKeys(Keys.DELETE);
    }

    public void findErrorMessage() {
        errorMessage.shouldBe(Condition.visible);
    }

}