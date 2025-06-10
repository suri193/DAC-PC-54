package com.cdac.acts;

import java.sql.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Database Table Manager ---");
            System.out.println("1. Create Table");
            System.out.println("2. Display Columns of a Table");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> createTable();
                    case 2 -> displayColumns();
                    case 3 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTable() throws SQLException {
        System.out.print("Enter table name: ");
        String tableName = scanner.nextLine();

        List<String> columnDefs = new ArrayList<>();
        List<String> columnNames = new ArrayList<>();
        String primaryKey = null;

        while (true) {
            System.out.println("\n1. Add Column");
            System.out.println("2. Set Primary Key");
            System.out.println("3. Save Table");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter column name: ");
                    String columnName = scanner.nextLine();

                    System.out.println("Select Data Type:");
                    System.out.println("1. VARCHAR");
                    System.out.println("2. INT");
                    System.out.println("3. FLOAT");
                    System.out.print("Choice: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    String dataType = switch (typeChoice) {
                        case 1 -> "VARCHAR(255)";
                        case 2 -> "INT";
                        case 3 -> "FLOAT";
                        default -> {
                            System.out.println("Invalid type, defaulting to VARCHAR.");
                            yield "VARCHAR(255)";
                        }
                    };

                    columnDefs.add(columnName + " " + dataType);
                    columnNames.add(columnName);
                    System.out.println("Column added.");
                }

                case 2 -> {
                    if (columnNames.isEmpty()) {
                        System.out.println("No columns to set as primary key.");
                    } else {
                        System.out.println("Choose column to set as Primary Key:");
                        for (int i = 0; i < columnNames.size(); i++) {
                            System.out.println((i + 1) + ". " + columnNames.get(i));
                        }
                        System.out.print("Choice: ");
                        int pkChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (pkChoice >= 1 && pkChoice <= columnNames.size()) {
                            primaryKey = columnNames.get(pkChoice - 1);
                            System.out.println("Primary Key set to: " + primaryKey);
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                }

                case 3 -> {
                    if (columnDefs.isEmpty()) {
                        System.out.println("No columns added. Cannot save table.");
                        return;
                    }

                    StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");
                    sql.append(String.join(", ", columnDefs));
                    if (primaryKey != null) {
                        sql.append(", PRIMARY KEY (" + primaryKey + ")");
                    }
                    sql.append(");");

                    try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
                        stmt.execute(sql.toString());
                        System.out.println("Table created successfully.");
                    }
                    return;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void displayColumns() throws SQLException {
        System.out.print("Enter table name: ");
        String tableName = scanner.nextLine();

        String sql = "SELECT * FROM " + tableName + " LIMIT 0";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            System.out.println("Columns in table '" + tableName + "':");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("- " + meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching columns. Table might not exist.");
        }
    }
}
