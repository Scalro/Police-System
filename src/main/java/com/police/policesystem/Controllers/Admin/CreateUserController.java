package com.police.policesystem.Controllers.Admin;

import com.police.policesystem.Models.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    public TextField categoryTfld;

    @FXML
    public Button createUserButton;

    @FXML
    public TextField firstNameTfld;

    @FXML
    public TextField lastNameTfld;

    @FXML
    public TextField passwordTfld;

    @FXML
    public TextField userNameTfld;

    Connection connection = null;
    PreparedStatement pst = null;
    public void AddUsers() {
        connection = DatabaseConnection.ConnectDb();
        String sql = "INSERT INTO users(firstName, lastName, userName, password, category) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, firstNameTfld.getText());
            pst.setString(2, lastNameTfld.getText());
            pst.setString(3, userNameTfld.getText());
            pst.setString(4, passwordTfld.getText());
            pst.setString(5, categoryTfld.getText());
            pst.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add");
            alert.setHeaderText(null);
            alert.setContentText("User Added Successfully!");
            alert.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException("Cannot Add User", e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { createUserButton.setOnAction(event -> AddUsers());}
}
