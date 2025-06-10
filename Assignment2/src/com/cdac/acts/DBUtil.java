package com.cdac.acts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = AppConfig.get("url");
        String user = AppConfig.get("username");
        String password = AppConfig.get("password");

        return DriverManager.getConnection(url, user, password);
    }
}
