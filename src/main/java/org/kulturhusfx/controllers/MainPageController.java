package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    // Egen launch metode?
    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {

        Parent adminMainPageParent = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            adminMainPageParent = fxmlLoader.load(getClass().getResource("adminMainPage.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }

        Scene adminMainPageScene = new Scene(adminMainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminMainPageScene);
        window.show();

        System.out.println("You clicked me!");
    }

    public void initialize() {
        // TODO
    }
}
