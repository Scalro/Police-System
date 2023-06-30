package com.police.policesystem.Controllers.Admin;

import com.samis.biometrics.Models.AddUser;
import com.samis.biometrics.Models.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable {

    @FXML
    public TableColumn<AddUser, String> categoryColumn;

    @FXML
    public TableColumn<AddUser, String> firstNameColumn;

    @FXML
    public TableColumn<AddUser, Integer> idColumn;

    @FXML
    public TableColumn<AddUser, String> lastNameColumn;

    @FXML
    public TableColumn<AddUser, String> passwordColumn;

    @FXML
    public TableColumn<AddUser, String> userNameColumn;

    @FXML
    public TableView<AddUser> viewUserTbl;

    @FXML
    public TextField search_txtfld;

    @FXML
    public Button refreshButton;
    @FXML
    public Button editButton;

    @FXML
    public void refreshButtonClicked() {
        refreshData();
        viewUserTbl.refresh(); // Refresh the table view to reflect the updated data
    }

    public void refreshData() {
        // Get the updated user list from the database
        ObservableList<AddUser> updatedUserList = DatabaseConnection.getUser();

        // Clear the existing data in listU
        listU.clear();

        // Add the updated user list to listU
        listU.addAll(updatedUserList);

        // Rebind the TableView items
        viewUserTbl.setItems(null);
        viewUserTbl.setItems(listU);
    }



    @FXML
    public void editUser() {
        AddUser selectedUser = viewUserTbl.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Show a prompt window or dialog to edit the user details
            // You can use JavaFX Dialog or create a custom dialog/prompt
            // Pass the selectedUser object to the prompt window/dialog for editing
        }

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit User");
        dialog.setHeaderText("Edit User Details");

        // Set the dialog buttons (e.g., OK and Cancel)
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create input fields for editing user details
        TextField firstNameField = new TextField(selectedUser.getFirstName());
        TextField lastNameField = new TextField(selectedUser.getLastName());
        TextField userNameField = new TextField(selectedUser.getUserName());
        TextField passwordField = new TextField(selectedUser.getPassword());
        TextField categoryField = new TextField(selectedUser.getCategory());

        // Create a grid pane and add the input fields
        GridPane gridPane = new GridPane();
        gridPane.add(firstNameField, 0, 0);
        gridPane.add(lastNameField, 1, 0);
        gridPane.add(userNameField, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(categoryField, 0, 2);

        dialog.getDialogPane().setContent(gridPane);

        // Wait for the user to click OK or Cancel
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // Retrieve the edited values from the input fields
                String editedFirstName = firstNameField.getText();
                String editedLastName = lastNameField.getText();
                String editedUserName = userNameField.getText();
                String editedPassword = passwordField.getText();
                String editedCategory = categoryField.getText();

                // Update the selectedUser object with the edited values
                selectedUser.setFirstName(editedFirstName);
                selectedUser.setLastName(editedLastName);
                selectedUser.setUserName(editedUserName);
                selectedUser.setPassword(editedPassword);
                selectedUser.setCategory(editedCategory);

                // Update the listU with the modified user object
                int index = listU.indexOf(selectedUser);
                if (index != -1) {
                    listU.set(index, selectedUser);
                }
            }
            return null;
        });

        dialog.showAndWait();
    }



    ObservableList<AddUser> listU;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Create the initial data list and populate it with the user data
        listU = FXCollections.observableArrayList(DatabaseConnection.getUser());
        viewUserTbl.setItems(listU);

        // Set up the filtering and sorting
        FilteredList<AddUser> filteredList = new FilteredList<>(listU, b -> true);
        search_txtfld.textProperty().addListener((observableValue, oldVal, newVal) -> {
            filteredList.setPredicate(user -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String searchText = newVal.toLowerCase();
                return user.getFirstName().toLowerCase().contains(searchText)
                        || user.getLastName().toLowerCase().contains(searchText)
                        || user.getUserName().toLowerCase().contains(searchText)
                        || user.getPassword().toLowerCase().contains(searchText)
                        || user.getCategory().toLowerCase().contains(searchText);
            });
        });

        SortedList<AddUser> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(viewUserTbl.comparatorProperty());

        viewUserTbl.setItems(sortedData);
    }
}