package netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;

    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {

        return new AuthInfo("petya", "123qwerty");
    }

    public static AuthInfo getInvalidUser() {
        Faker faker = new Faker();
        String inValidLogin = getValidAuthInfo().login;
        String inValidPassword = faker.internet().password().toString();

        return new AuthInfo(inValidLogin, inValidPassword);
    }


}
