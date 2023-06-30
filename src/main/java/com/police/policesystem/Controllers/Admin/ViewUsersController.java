package com.police.policesystem.Controllers.Admin;

import com.police.policesystem.Models.AddUser;
import com.police.policesystem.Models.DatabaseConnection;
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
        listU.clear();
        listU.addAll(DatabaseConnection.getUser());
        viewUserTbl.refresh();
    }




    @FXML
    public void editUser() {
        AddUser selectedUser = viewUserTbl.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {}

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit User");
        dialog.setHeaderText("Edit User Details");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField firstNameField = new TextField(selectedUser.getFirstName());
        TextField lastNameField = new TextField(selectedUser.getLastName());
        TextField userNameField = new TextField(selectedUser.getUserName());
        TextField passwordField = new TextField(selectedUser.getPassword());
        TextField categoryField = new TextField(selectedUser.getCategory());

        GridPane gridPane = new GridPane();
        gridPane.add(firstNameField, 0, 0);
        gridPane.add(lastNameField, 1, 0);
        gridPane.add(userNameField, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(categoryField, 0, 2);

        dialog.getDialogPane().setContent(gridPane);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String editedFirstName = firstNameField.getText();
                String editedLastName = lastNameField.getText();
                String editedUserName = userNameField.getText();
                String editedPassword = passwordField.getText();
                String editedCategory = categoryField.getText();

                selectedUser.setFirstName(editedFirstName);
                selectedUser.setLastName(editedLastName);
                selectedUser.setUserName(editedUserName);
                selectedUser.setPassword(editedPassword);
                selectedUser.setCategory(editedCategory);


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


        listU = FXCollections.observableArrayList(DatabaseConnection.getUser());
        viewUserTbl.setItems(listU);

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