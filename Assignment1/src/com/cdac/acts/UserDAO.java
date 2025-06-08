package com.cdac.acts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCity());
            statement.executeUpdate();
        }
    }

    public List<User> getUsersByCity(String city) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE city = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, city);
            ResultSet userinput = statement.executeQuery();
            while (userinput.next()) {
                users.add(new User(
                    userinput.getString("username"),
                    userinput.getString("password"),
                    userinput.getString("name"),
                    userinput.getString("email"),
                    userinput.getString("city")
                ));
            }
        }
        return users;
    }

    public boolean updatePassword(String username, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            return stmt.executeUpdate() > 0;
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet userinput = stmt.executeQuery();
            if (userinput.next()) {
                return new User(
                    userinput.getString("username"),
                    userinput.getString("password"),
                    userinput.getString("name"),
                    userinput.getString("email"),
                    userinput.getString("city")
                );
            }
        }
        return null;
    }
}

