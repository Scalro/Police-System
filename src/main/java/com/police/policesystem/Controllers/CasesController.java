package com.police.policesystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CasesController implements Initializable {

    @FXML
    public TableColumn<?, ?> accCasesTbl;

    @FXML
    public TableView<?> casesTbl;

    @FXML
    public TableColumn<?, ?> closedCasesTbl;

    @FXML
    public TableColumn<?, ?> idTbl;

    @FXML
    public TableColumn<?, ?> openCasesTbl;

    @FXML
    public TableColumn<?, ?> otherCasesTbl;

    @FXML
    public TableColumn<?, ?> p3CasesTbl;

    @FXML
    public TextField searchTfld;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
