package com.police.policesystem.Controllers;

import com.police.policesystem.Models.DatabaseConnection;
import com.police.policesystem.Models.Model;
import com.police.policesystem.Sessions.Session;
import com.police.policesystem.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class LoginController implements Initializable {
    @FXML
    public ChoiceBox<AccountType> acc_choice;
    @FXML
    public Label err_lbl;
    @FXML
   public Button lgn_btn;
    @FXML
    public TextField lgn_username_field;
    @FXML
    public PasswordField login_passw_fld;
    @FXML
    public Button cancelButton;

    private UnaryOperator<TextFormatter.Change> createNumericFilter() {
        return change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_choice.setItems(FXCollections.observableArrayList(AccountType.USER, AccountType.ADMIN));
        acc_choice.setValue(Model.getInstance().getViewsFactory().getLoginAccountType());
        acc_choice.valueProperty().addListener(observable -> Model.getInstance().getViewsFactory().setLoginAccountType(acc_choice.getValue()));
        lgn_btn.setOnAction(event ->onLogin());
        cancelButton.setOnAction(event -> onExit());
        lgn_username_field.setTextFormatter(new TextFormatter<>(createNumericFilter()));
    }
    private void onLogin(){
        Stage stage = (Stage) err_lbl.getScene().getWindow();
        if (Model.getInstance().getViewsFactory().getLoginAccountType() == AccountType.USER) {
            if (!(lgn_username_field.getText().isBlank() && login_passw_fld.getText().isBlank())) {
                if (validateULogin()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Logged In successfully!");
                    alert.showAndWait();
                    Session.savePreference("username", lgn_username_field.getText());
                    Model.getInstance().getViewsFactory().showUserWindow();
                    Model.getInstance().getViewsFactory().closeStage(stage);
                } else {
                    err_lbl.setText("Wrong username or password");
                }
            } else {
                err_lbl.setText("Please enter username and password");
            }
        } else {
            if (Model.getInstance().getViewsFactory().getLoginAccountType() == AccountType.ADMIN) {
                if (!(lgn_username_field.getText().isBlank() && login_passw_fld.getText().isBlank())) {
                    if (validateALogin()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Logged In successfully!");
                        alert.showAndWait();
                        Model.getInstance().getViewsFactory().showAdminWidow();
                        Model.getInstance().getViewsFactory().closeStage(stage);
                    } else {
                        err_lbl.setText("Wrong username or password");
                    }
                }
            }
        }
    }

    public void onExit(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public boolean validateULogin() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connection = connect.ConnectDb();

        String verifyLogin = "SELECT count(1) FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(verifyLogin);
            statement.setString(1, lgn_username_field.getText());
            statement.setString(2, login_passw_fld.getText());
            ResultSet rs = statement.executeQuery();

            if (rs.next() && rs.getInt(1) == 1) {
                err_lbl.setText("Login Successful");
                return true;
            } else {
                err_lbl.setText("Wrong username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean validateALogin() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connection = connect.ConnectDb();

        String verifyLogin = "SELECT count(1) FROM admin WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(verifyLogin);
            statement.setString(1, lgn_username_field.getText());
            statement.setString(2, login_passw_fld.getText());
            ResultSet rs = statement.executeQuery();

            if (rs.next() && rs.getInt(1) == 1) {
                err_lbl.setText("Login Successful");
                return true;
            } else {
                err_lbl.setText("Wrong username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
