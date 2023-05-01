package netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static QueryRunner runner = new QueryRunner();

    private DBHelper() {

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }


    public static DataHelper.VerificationCode getAuthCode() {
        var authCodeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection()) {
            var code = runner.query(conn, authCodeSQL, new ScalarHandler<String>());
            return new DataHelper.VerificationCode(code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void removeDB() {
        var conn = getConnection();

        runner.execute(conn, "DELETE FROM auth_codes;");
        runner.execute(conn, "DELETE FROM card_transactions;");
        runner.execute(conn, "DELETE FROM cards;");
        runner.execute(conn, "DELETE FROM users;");
    }

}
