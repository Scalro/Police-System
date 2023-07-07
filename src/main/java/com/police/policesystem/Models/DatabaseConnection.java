package com.police.policesystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class DatabaseConnection {
    public static Connection databaseLink;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PoliceRecords";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

        public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Connected");
        }catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Database");
            alert.setHeaderText(null);
            alert.setContentText("Can't connect to Database!");
            alert.showAndWait();
        }
        return databaseLink;
    }



    public static ObservableList<AddUser> getUser() {
        Connection connection = ConnectDb();
        ObservableList<AddUser> listU = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Extract data from the ResultSet and create an AddUser object
                listU.add(new AddUser(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("category")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listU;
    }
}
