package com.police.policesystem.Controllers;

import com.police.policesystem.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public BorderPane user_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "Attendance" -> user_parent.setCenter(Model.getInstance().getViewsFactory().getCaseReportAView());
                case "AddStudent" -> user_parent.setCenter(Model.getInstance().getViewsFactory().getCaseReportBView());
                case "ScanFingers" -> user_parent.setCenter(Model.getInstance().getViewsFactory().getAccidentReportView());
                default -> user_parent.setCenter(Model.getInstance().getViewsFactory().getDashboardView());
            }
        } );
    }
}
