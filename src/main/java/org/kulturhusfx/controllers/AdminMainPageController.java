package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminMainPageController {

    //@FXML
    //private Label label;

    @FXML
    private void handleAdminLoginButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

    public void initialize() {
        // TODO
    }
}
