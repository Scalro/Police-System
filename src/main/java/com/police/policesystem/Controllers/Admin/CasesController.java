package com.police.policesystem.Controllers.Admin;

import com.police.policesystem.Models.Case;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CasesController implements Initializable {

    @FXML
    public TableView<Case> casesTbl;

    @FXML
    public TableColumn<Case, Integer> id;

    @FXML
    public TableColumn<Case, String> caseRefNo;

    @FXML
    public TableColumn<Case, String> dateReported;

    @FXML
    public TextField searchTfld;

    @FXML
    public Button deleteButton;

    @FXML
    public Button updateButton;

    ObservableList<Case> casesData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the ObservableList to hold case data
        casesData = FXCollections.observableArrayList();

        // Set the cell value factories for each TableColumn
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        caseRefNo.setCellValueFactory(new PropertyValueFactory<>("caseRefNo"));
        dateReported.setCellValueFactory(new PropertyValueFactory<>("dateReported"));

        // Populate the TableView with data from the database
        fetchCasesData();

        // Bind the casesData list to the TableView
        casesTbl.setItems(casesData);

        // Set up the deleteButton action
        deleteButton.setOnAction(this::handleDeleteButtonAction);

        updateButton.setOnAction(this::handleUpdateButtonAction);
    }

    public void fetchCasesData() {
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoliceRecords", "root", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM cases")) {

            // Iterate through the result set and add Case objects to casesData
            while (resultSet.next()) {
                Integer caseIdValue = resultSet.getInt("id");
                String caseRefNoValue = resultSet.getString("caseRefNo");
                String dateReportedValue = resultSet.getString("dateReported");

                    Case caseObj = new Case(caseIdValue, caseRefNoValue, dateReportedValue);
                    casesData.add(caseObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void handleDeleteButtonAction(ActionEvent event) {
        // Get the selected Case from the TableView
        Case selectedCase = casesTbl.getSelectionModel().getSelectedItem();

        if (selectedCase != null) {
            // Connect to the database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoliceRecords", "root", "");
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM cases WHERE caseRefNo = ?")) {

                // Set the caseRefNo value for the DELETE query
                statement.setString(1, selectedCase.getCaseRefNo());

                // Execute the DELETE query
                statement.executeUpdate();

                // Remove the selected Case from casesData
                casesData.remove(selectedCase);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateButtonAction(ActionEvent event) {
        // Get the selected Case from the TableView
        Case selectedCase = casesTbl.getSelectionModel().getSelectedItem();

        if (selectedCase != null) {
            // Open a new window or dialog for updating the case information
            // You can display the existing data and allow the user to modify it
            // For example, you can use a new FXML file and a controller for the update window/dialog

            // After the update, you can retrieve the updated data and update the TableView
            // Connect to the database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoliceRecords", "root", "");
                 PreparedStatement statement = connection.prepareStatement("UPDATE cases SET id = ?, dateReported = ? WHERE caseRefNo = ?")) {

                // Set the updated values for the UPDATE query
                statement.setInt(1, selectedCase.getId());
                statement.setString(2, selectedCase.getDateReported());
                statement.setString(3, selectedCase.getCaseRefNo());

                // Execute the UPDATE query
                statement.executeUpdate();

                // Refresh the TableView to reflect the updated data
                casesTbl.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
