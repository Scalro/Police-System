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
                case CASEREPORTA -> user_parent.setCenter(Model.getInstance().getViewsFactory().getCaseReportAView());
                case CASEREPORTB -> user_parent.setCenter(Model.getInstance().getViewsFactory().getCaseReportBView());
                case ACCIDENTREPORT -> user_parent.setCenter(Model.getInstance().getViewsFactory().getAccidentReportView());
                case P3REPORT -> user_parent.setCenter(Model.getInstance().getViewsFactory().getP3ReportView());
                case CASES -> user_parent.setCenter(Model.getInstance().getViewsFactory().getCasesView());
                default -> user_parent.setCenter(Model.getInstance().getViewsFactory().getDashboardView());
            }
        } );
    }
}
