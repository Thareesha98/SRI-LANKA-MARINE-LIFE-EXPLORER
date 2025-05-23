/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

/**
 *
 * @author thareesha
 */


import java.sql.*;

public class MySQLTest {
    private static final String URL = "jdbc:mysql://localhost:3306/marine";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to the database.");

            // 1. Create table if not exists
            String createTableSQL =
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100)," +
                    "email VARCHAR(100))";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("🧱 Table 'users' is ready.");
            }

            // 2. Insert sample data
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Alice");
                pstmt.setString(2, "alice@example.com");
                pstmt.executeUpdate();

                pstmt.setString(1, "Bob");
                pstmt.setString(2, "bob@example.com");
                pstmt.executeUpdate();

                System.out.println("📥 Sample data inserted.");
            }

            // 3. Retrieve and print data
            String selectSQL = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                System.out.println("📊 Data in 'users' table:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.printf("ID: %d, Name: %s, Email: %s%n", id, name, email);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Database error:");
            e.printStackTrace();
        }
    }
}

