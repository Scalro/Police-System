package com.police.policesystem.Views;

import com.police.policesystem.Controllers.Admin.AdminController;
import com.police.policesystem.Controllers.UserController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AccountType loginAccountType;

    private final ObjectProperty<UserMenuOption> userSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane caseReportAView;
    private AnchorPane caseReportBView;
    private AnchorPane accidentReportView;
    private AnchorPane p3ReportView;
    private AnchorPane casesView;


    /* Admin */
    private final ObjectProperty<AdminMenuOption> adminSelectedMenuItem;
    private AnchorPane createUserView;
    private AnchorPane viewUsersView;
    private AnchorPane editView;
    public ViewFactory(){
        this.loginAccountType = AccountType.USER;
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<UserMenuOption> getUserSelectedMenuItem() {
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

    public AnchorPane getP3ReportView() {
        if (p3ReportView == null) {
            try {
                p3ReportView = new FXMLLoader(getClass().getResource("/Fxml/P3Form.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return p3ReportView;
    }

    public AnchorPane getCasesView() {
        if (casesView == null) {
            try {
                casesView= new FXMLLoader(getClass().getResource("/Fxml/Cases.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return casesView;
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
    /*Admin*/

    public ObjectProperty<AdminMenuOption> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public void showAdminWidow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public AnchorPane getCreateUserView(){

        if (createUserView == null) {
            try {
                createUserView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateUser.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return createUserView;
    }

    public AnchorPane getViewUsersView() {
        if (viewUsersView == null) {
            try {
                viewUsersView = new FXMLLoader(getClass().getResource("/Fxml/Admin/ViewUsers.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return viewUsersView;
    }

    public AnchorPane getEditView() {
        if (editView == null) {
            try {
                editView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Edit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return editView;
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
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/logo.png"))));
        stage.setResizable(false);
        stage.setTitle("Samis");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
}