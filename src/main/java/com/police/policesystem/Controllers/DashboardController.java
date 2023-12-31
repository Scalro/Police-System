package com.police.policesystem.Controllers;

import com.police.policesystem.Models.Case;
import com.police.policesystem.Sessions.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.police.policesystem.Models.DatabaseConnection.ConnectDb;

public class DashboardController implements Initializable {

    @FXML
    public Label dateLabel;
    @FXML
    public Label username_lbl;
    public void setLoggedUsername(String username) {username_lbl.setText(username);}
    public void setDateLabel() { dateLabel.setText(LocalDate.now().toString());}
    public void hello() {
        System.out.println("Working");

        String username = Session.getPreference("username", "");
        setLoggedUsername(username);
        Session.removePreference("username");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateLabel();
        hello();

    }


}
