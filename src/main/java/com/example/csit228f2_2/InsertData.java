package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (name, email, password) VALUES (?, ?, ?) RETURNING *" )) {
//            c.setAutoCommit(false);
            String name = "new one";
            String email = "jayvince@gmail.com";
            String password = "\2\0";
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, password.hashCode());
//            int rowsInserted = statement.executeUpdate();
            statement.addBatch();
//            int sample = 13/0;
            String name2 = "another one";
            String email2 = "another@gmail.com";
            String password2 = "another";
            statement.setString(1, name2);
            statement.setString(2, email2);
            statement.setInt(3, password2.hashCode());
//            rowsInserted = statement.executeUpdate();
            statement.addBatch();
            int[] rowsInserted = statement.executeBatch();
//            c.commit();
            System.out.println("INSERTED: " + rowsInserted);
            ResultSet res = statement.getResultSet();
            while (res.next()) {
                System.out.println("ID: " + res.getInt(1));
                System.out.println("Name: " + res.getString("name"));
                System.out.println("Email: " + res.getString("email"));
                String db_pw = res.getString("password");
                System.out.println("Password: " + db_pw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
