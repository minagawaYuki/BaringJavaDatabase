package com.example.csit228f2_2;

import java.sql.*;

public class CRUD {
    public static boolean isValidLogin(User user) {

        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement("SELECT * FROM users WHERE name=?");) {
            String name = user.username;
            statement.setString(1, user.username);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                if(res.getString("password").equals(user.password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void registerUser(User user) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (name, email, password) VALUES (?, ?, ?)" )) {
//            c.setAutoCommit(false);
            String name = user.username;
            String email = user.email;
            String password = user.password;
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
//            int rowsInserted = statement.executeUpdate();

            int rowsInserted = statement.executeUpdate();
//            c.commit();
            System.out.println("INSERTED: " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
