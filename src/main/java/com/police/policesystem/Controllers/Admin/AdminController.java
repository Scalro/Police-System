package com.police.policesystem.Controllers.Admin;

import com.police.policesystem.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewsFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case VIEW_USERS -> admin_parent.setCenter(Model.getInstance().getViewsFactory().getViewUsersView());
                case EDIT -> admin_parent.setCenter(Model.getInstance().getViewsFactory().getEditView());
                default -> admin_parent.setCenter(Model.getInstance().getViewsFactory().getCreateUserView());

            }
        } );
    }
}
