package com.police.policesystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FilledFormController implements Initializable {
    // Define FXML fields


    @FXML
    public TextField a1;

    @FXML
    public TextField a5;

    @FXML
    public TextField a2;

    @FXML
    public TextField a3;

    @FXML
    public TextField a4;
    @FXML
    public TextField a6;

    @FXML
    public TextField a7;

    @FXML
    public TextField a8;

    @FXML
    public TextArea a9;

    @FXML
    public TextField caseRefNo;

    @FXML
    public TextField dateReported;


    // Define methods to set and retrieve field values
    public void setFieldValues(String a1Value, String a2Value, String caseRefNoValue, String a3Value, String a4Value,
                               String dateReportedValue, String a5Value, String a6Value, String a7Value, String a8Value,String a9Value) {
        // Set the field values in the respective UI components
        a1.setText(a1Value);
        a2.setText(a2Value);
        caseRefNo.setText(caseRefNoValue);
        a3.setText(a3Value);
        a4.setText(a4Value);
        dateReported.setText(dateReportedValue);
        a5.setText(a5Value);
        a6.setText(a6Value);
        a7.setText(a7Value);
        a8.setText(a8Value);
        a9.setText(a9Value);
    }

    public String getFieldValue(String fieldName) {
        // Retrieve the value from the respective UI component based on the field name
        switch (fieldName) {
            case "a1":
                return a1.getText();
            case "a2":
                return a2.getText();
            case "a3":
                return caseRefNo.getText();
            case "a4":
                return a3.getText();
            case "a5":
                return a4.getText();
            case "a6":
                return dateReported.getText();
            case "a7":
                return a5.getText();
            case "a8":
                return a6.getText();
            case "a9":
                return a7.getText();
            case "a10":
                return a8.getText();
            case "a11":
                return a9.getText();
            default:
                return "";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ...
}
