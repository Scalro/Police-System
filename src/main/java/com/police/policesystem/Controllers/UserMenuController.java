package com.police.policesystem.Controllers;

import com.police.policesystem.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    public Button userprofile_btn;
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
    }
    public void onDashboard(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set("Dashboard");
    }
    public void onCaseReportA(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set("CaseReportA");
    }

    public void onCaseReportB(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set("CaseReportB");
    }
    public void onAccidentReport(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set("AccidentReport");
    }
    public void onP3Report(){
        Model.getInstance().getViewsFactory().getUserSelectedMenuItem().set("P3Report");
    }
}
