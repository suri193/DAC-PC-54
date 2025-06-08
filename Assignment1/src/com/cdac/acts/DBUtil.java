package com.cdac.acts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = UserConfig.get("db.url");
        String user = UserConfig.get("db.username");
        String password = UserConfig.get("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}


