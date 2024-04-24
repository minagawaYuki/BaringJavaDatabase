package com.example.csit228f2_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET password=? WHERE password=? "
             )){
            String defaultPw = "something else2";
            String oldPw = "something else";
            statement.setInt(1, defaultPw.hashCode());
            statement.setInt(2, oldPw.hashCode());
            int rowsUpdated = statement.executeUpdate();
            System.out.println("rowsUpdated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
