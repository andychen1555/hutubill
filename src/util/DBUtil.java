package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class DBUtil {

    public static String ip = "127.0.0.1";
    public static int port = 3306;
    public static String database = "hutubill";
    static String encoding = "UTF-8";
    public static String loginName = "root";
    public static String password = "123";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

}
