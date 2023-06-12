package com.police.policesystem.Views;

import com.police.policesystem.Controllers.UserController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewsFactory {
    private final StringProperty userSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane caseReportAView;
    private AnchorPane caseReportBView;
    private AnchorPane accidentReportView;

    public ViewsFactory(){
        this.userSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getUserSelectedMenuItem() {
        return userSelectedMenuItem;
    }

    public AnchorPane getDashboardView(){

        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getCaseReportAView(){
        if (caseReportAView == null) {
            try {
                caseReportAView = new FXMLLoader(getClass().getResource("/Fxml/CaseReportA.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return caseReportAView;
    }

    public AnchorPane getCaseReportBView(){
        if (caseReportBView == null) {
            try {
                caseReportBView = new FXMLLoader(getClass().getResource("/Fxml/CaseReportB.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return caseReportBView;
    }

    public AnchorPane getAccidentReportView() {
        if (accidentReportView == null) {
            try {
                accidentReportView = new FXMLLoader(getClass().getResource("/Fxml/AccidentReport.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accidentReportView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    public void showUserWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/User.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);

        createStage(loader);
    }

    public void createStage(FXMLLoader loader){

        Scene scene = null;

        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Samis");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}
