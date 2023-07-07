package com.police.policesystem.Controllers;

import com.police.policesystem.Models.Model;
import com.police.policesystem.Views.UserMenuOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;



public class UserMenuController implements Initializable {

    @FXML
    public Button accidentreport_btn;

    @FXML
    public Button casesreport_btn;
    @FXML
    public Button casesreport_btn1;
    @FXML
    public Button dashboard_btn;
    @FXML
    public Button logout_btn;
    @FXML
    public Button p3report_btn;
    @FXML
    public Button report_btn;
    @FXML
    public Button casesButton;
    @FXML
    public void setLogout_btn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully!");
        alert.showAndWait();
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners(){
        dashboard_btn.setOnAction(event -> onDashboard());
        casesreport_btn.setOnAction(event -> onCaseReportA());
        casesreport_btn1.setOnAction(event -> onCaseReportB());
        accidentreport_btn.setOnAction(event ->  onAccidentReport());
        p3report_btn.setOnAction(event -> onP3Report());
        casesButton.setOnAction(event -> onCases());
    }
    public void onDashboard(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.DASHBOARD);
    }
    public void onCaseReportA(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.CASEREPORTA);
    }

    public void onCaseReportB(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.CASEREPORTB);
    }
    public void onAccidentReport(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.ACCIDENTREPORT);
    }
    public void onP3Report(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.P3REPORT);
    }

    public void onCases(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set(UserMenuOption.CASES);
    }
}
