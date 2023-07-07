package com.police.policesystem.Controllers;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.Node;


public class P3FormController {



    @FXML
    public AnchorPane anchorPane;

    @FXML
    public void initialize() {
        showPage("page1"); // Show the initial page (page1)
    }

    private void showPage(String pageId) {
        Node page = getPageNode(pageId);
        anchorPane.getChildren().forEach(node -> node.setVisible(node == page));
    }

    private Node getPageNode(String pageId) {
        switch (pageId) {
            case "page1":
                return anchorPane.lookup("page1");
            case "page2":
                return anchorPane.lookup("page2");
            case "page3":
                return anchorPane.lookup("page3");
            case "page4":
                return anchorPane.lookup("page4");
            default:
                throw new IllegalArgumentException("Invalid pageId: " + pageId);
        }
    }

    // Add event handlers or methods to switch between pages as needed
}

