package kr.java.dao.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() {
        System.out.println("DB Connection 시작");
        try {
            // 1단계 - 드라이버를 인식
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2단계
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            String DB_URL = dotenv.get("DB_URL");
            String DB_USER = dotenv.get("DB_USER");
            String DB_PW = dotenv.get("DB_PW");
            // Properties - 유사 map (key, value)
            Properties properties = new Properties();
            properties.setProperty("user", DB_USER);
            properties.setProperty("password", DB_PW);

            Connection conn =  DriverManager.getConnection(
                    DB_URL,
                    properties
            );
            System.out.println("DB Connection 성공");
            return conn;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
