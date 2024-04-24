package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteUser {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "DELETE FROM users WHERE id<? RETURNING *"
             )) {
            int id = 4;
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Deleted rows: " + rowsDeleted );

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
        }
    }
}
