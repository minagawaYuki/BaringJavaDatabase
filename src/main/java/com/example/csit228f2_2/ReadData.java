package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReadData {
    public static void main(String[] args) {
        System.out.println("Enter password: ");
        Scanner sc = new Scanner(System.in);
        String pw = sc.nextLine();
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement();) {
            String query = "SELECT * FROM users WHERE password IS NULL";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                System.out.println("ID: " + res.getInt(1));
                System.out.println("Name: " + res.getString("name"));
                System.out.println("Email: " + res.getString("email"));
                String db_pw = res.getString("password");
                System.out.println("Password: " + db_pw);
                if (db_pw!=null && pw.hashCode() == Integer.parseInt(db_pw)) {
                    System.out.println("SUCCESS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
