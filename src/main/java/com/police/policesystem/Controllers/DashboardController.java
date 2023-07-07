package com.police.policesystem.Controllers;

import com.police.policesystem.Sessions.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public Label accidentCases;

    @FXML
    public Label accidentClosed;

    @FXML
    public Label openCasesCard;

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
        Map<String, Integer> caseCounts = getCaseCounts();

        // Update the labels with the case counts
        accidentCases.setText(String.valueOf(caseCounts.getOrDefault("accident", 0)));
        accidentClosed.setText(String.valueOf(caseCounts.getOrDefault("accidentClosed", 0)));
        openCasesCard.setText(String.valueOf(caseCounts.getOrDefault("open", 0)));
        openClosed.setText(String.valueOf(caseCounts.getOrDefault("openClosed", 0)));
        otherCases.setText(String.valueOf(caseCounts.getOrDefault("other", 0)));
        otherClosed.setText(String.valueOf(caseCounts.getOrDefault("otherClosed", 0)));
        p3Cases.setText(String.valueOf(caseCounts.getOrDefault("p3", 0)));
        p3Closed.setText(String.valueOf(caseCounts.getOrDefault("p3Closed", 0)));
    }

    public static Map<String, Integer> getCaseCounts() {
        Map<String, Integer> caseCounts = new HashMap<>();

        try {
            Connection connection = ConnectDb();

            // Query to get the case counts
            String query = "SELECT category, COUNT(*) AS count FROM cases GROUP BY category";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                int count = resultSet.getInt("count");
                caseCounts.put(category, count);
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return caseCounts;
    }


}
