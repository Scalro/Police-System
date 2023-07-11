package com.police.policesystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FilledFormBController implements Initializable {
    // Define FXML fields


    @FXML
    public TextField b1;

    @FXML
    public TextField b2;

    @FXML
    public TextField b3;

    @FXML
    public TextField b4;

    @FXML
    public TextField b5;
    @FXML
    public TextField b6;

    @FXML
    public TextField b7;

    @FXML
    public TextField b8;

    @FXML
    public TextArea b9;



    // Define methods to set and retrieve field values
    public void setFieldValues(String a1Value, String a2Value, String caseRefNoValue, String a3Value, String a4Value,
                               String dateReportedValue, String a5Value, String a6Value, String a7Value) {
        // Set the field values in the respective UI components
        b1.setText(a1Value);
        b2.setText(a2Value);
        b3.setText(caseRefNoValue);
        b4.setText(a3Value);
        b5.setText(a4Value);
        b6.setText(dateReportedValue);
        b7.setText(a5Value);
        b8.setText(a6Value);
        b9.setText(a7Value);
    }

    public String getFieldValue(String fieldName) {
        // Retrieve the value from the respective UI component based on the field name
        switch (fieldName) {
            case "a1":
                return b1.getText();
            case "a2":
                return b2.getText();
            case "a3":
                return b3.getText();
            case "a4":
                return b4.getText();
            case "a5":
                return b5.getText();
            case "a6":
                return b6.getText();
            case "a7":
                return b7.getText();
            case "a8":
                return b8.getText();
            case "a9":
                return b9.getText();
            case "a10":
            default:
                return "";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ...
}
