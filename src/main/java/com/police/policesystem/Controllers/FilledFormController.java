package com.police.policesystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FilledFormController implements Initializable {
    // Define FXML fields

    @FXML
    public TextField a1TextField;

    @FXML
    public TextField a11TextField;

    @FXML
    public TextField a10TextField;

    @FXML
    public TextField a2TextField;

    @FXML
    public TextField a3TextField;

    @FXML
    public TextField a4TextField;

    @FXML
    public TextField a5TextField;

    @FXML
    public TextField a6TextField;

    @FXML
    public TextField a7TextField;

    @FXML
    public TextField a8TextField;

    @FXML
    public TextField a9TextField;


    // Define methods to set and retrieve field values
    public void setFieldValues(String a1Value, String a2Value, String a3Value, String a4Value, String a5Value,
                               String a6Value, String a7Value, String a8Value, String a9Value, String a10Value,
                               String a11Value) {
        // Set the field values in the respective UI components
        a1TextField.setText(a1Value);
        a2TextField.setText(a2Value);
        a3TextField.setText(a3Value);
        a4TextField.setText(a4Value);
        a5TextField.setText(a5Value);
        a6TextField.setText(a6Value);
        a7TextField.setText(a7Value);
        a8TextField.setText(a8Value);
        a9TextField.setText(a9Value);
        a10TextField.setText(a10Value);
        a11TextField.setText(a11Value);
    }

    public String getFieldValue(String fieldName) {
        // Retrieve the value from the respective UI component based on the field name
        switch (fieldName) {
            case "a1":
                return a1TextField.getText();
            case "a2":
                return a2TextField.getText();
            case "a3":
                return a3TextField.getText();
            case "a4":
                return a4TextField.getText();
            case "a5":
                return a5TextField.getText();
            case "a6":
                return a6TextField.getText();
            case "a7":
                return a7TextField.getText();
            case "a8":
                return a8TextField.getText();
            case "a9":
                return a9TextField.getText();
            case "a10":
                return a10TextField.getText();
            case "a11":
                return a11TextField.getText();
            default:
                return "";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ...
}
