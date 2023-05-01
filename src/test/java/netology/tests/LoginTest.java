package netology.tests;

import netology.pages.LoginPage;
import netology.data.DBHelper;
import netology.data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {


    @BeforeEach

    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public void cleanBD() {
        DBHelper.removeDB();
    }


    @Test
    void shouldSuccessLogin() {
        var authInfo = DataHelper.getValidAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DBHelper.getAuthCode();
        verificationPage.validVerify(verificationCode.getCode());

    }

    @Test
    void shouldBlockUser() {
        var authInfo = DataHelper.getInvalidUser();
        var loginPage = new LoginPage();
        loginPage.validLogin(authInfo);
        loginPage.clearFields();
        loginPage.validLogin(authInfo);
        loginPage.clearFields();
        loginPage.validLogin(authInfo);
        loginPage.clearFields();

        loginPage.validLogin(DataHelper.getValidAuthInfo());
        loginPage.findErrorMessage();

    }

}
