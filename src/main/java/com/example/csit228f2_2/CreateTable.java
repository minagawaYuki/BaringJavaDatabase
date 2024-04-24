package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
    public static void main(String[] args) {
        // try-with-resources block
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY," +
                             "name VARCHAR(50) NOT NULL," +
                             "email VARCHAR(100) NOT NULL," +
                             "password VARCHAR(50) )")) {
            statement.execute();
            // error
            System.out.println("TABLE created successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
