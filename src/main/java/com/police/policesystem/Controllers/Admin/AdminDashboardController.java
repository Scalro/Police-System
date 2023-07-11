package com.police.policesystem.Controllers.Admin;

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
import java.util.ResourceBundle;

import static com.police.policesystem.Models.DatabaseConnection.ConnectDb;

public class AdminDashboardController implements Initializable {

    @FXML
    public Label accidentCases;

    @FXML
    public Label accidentClosed;

    @FXML
    public Label openCases;

    @FXML
    public Label openClosed;

    @FXML
    public Label otherCases;

    @FXML
    public Label otherClosed;

    @FXML
    public Label p3Cases;

    @FXML
    public Label p3Closed;

    @FXML
    public Label dateLabel;
    @FXML
    public Label username_lbl;
    @FXML
    public PieChart pieChart;
    public void setLoggedUsername(String username) {username_lbl.setText(username);}
    public void setDateLabel() { dateLabel.setText(LocalDate.now().toString());}
    public void hello() {
        System.out.println("Working");

        // Retrieve a preference
        String username = Session.getPreference("username", "");
        setLoggedUsername(username);


        // Remove a preference
        Session.removePreference("username");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateLabel();
        hello();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Accident Cases", 20),
                new PieChart.Data("Open Cases", 30),
                new PieChart.Data("Other Cases", 15)
//                new PieChart.Data("P3 Cases,4")

        );

        // Set the data to the PieChart
        pieChart.setData(pieChartData);
    }

}
