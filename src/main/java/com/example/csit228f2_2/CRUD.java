package com.example.csit228f2_2;

import java.sql.*;

public class CRUD {
    public static void createUserTable() {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY," +
                             "name VARCHAR(50) NOT NULL," +
                             "email VARCHAR(100) NOT NULL," +
                             "password VARCHAR(50) )");) {
            statement.addBatch();
            // error
            System.out.println("TABLE created successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createNotesTable() {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS notes (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY," +
                             "notes VARCHAR(50))");) {
            statement.addBatch();
            statement.executeBatch();
            // error
            System.out.println("TABLE created successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
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
    public static void createNote(String note) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO notes (notes) VALUES (?)")) {
//            c.setAutoCommit(false);

            statement.setString(1, note);
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
    public static ResultSet getUserNotes() {
        ResultSet res = null;
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement();) {
            String query = "SELECT * FROM notes";
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
