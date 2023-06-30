package com.police.policesystem.Controllers.Admin;

import com.police.policesystem.Models.Model;
import com.police.policesystem.Views.AdminMenuOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    @FXML
    public Button create_user_btn;

    @FXML
    public Button view_user_btn;
    @FXML
    public Button logoutButton;

    @FXML
    public void setLogoutButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully!");
        alert.showAndWait();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners(){
        create_user_btn.setOnAction(event -> onCreateUser());
        view_user_btn.setOnAction(event -> onViewUser());
    }

    public void onCreateUser(){
        Model.getInstance().getViewsFactory().getAdminSelectedMenuItem().set(AdminMenuOption.CREATE_USER);
    }
    public void onViewUser(){
        Model.getInstance().getViewsFactory().getAdminSelectedMenuItem().set(AdminMenuOption.VIEW_USERS);
    }
}
