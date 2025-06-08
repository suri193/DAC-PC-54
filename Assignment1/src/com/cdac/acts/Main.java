package com.cdac.acts;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. Register User");
            System.out.println("2. List Users by City");
            System.out.println("3. Update Password");
            System.out.println("4. Display User Info");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> registerUser();
                    case 2 -> listUsersByCity();
                    case 3 -> updatePassword();
                    case 4 -> displayUserInfo();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerUser() throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();

        User user = new User(username, password, name, email, city);
        userDAO.registerUser(user);
        System.out.println("User registered successfully.");
    }

    private static void listUsersByCity() throws SQLException {
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        List<User> users = userDAO.getUsersByCity(city);
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(user -> System.out.println(user.getUsername() + " - " + user.getName()));
        }
    }

    private static void updatePassword() throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        boolean updated = userDAO.updatePassword(username, newPassword);
        if (updated) {
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void displayUserInfo() throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("City: " + user.getCity());
        } else {
            System.out.println("User not found.");
        }
    }
}
