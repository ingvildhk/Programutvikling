package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class MainPageController {

    // Egen launch metode?
    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent adminMainPageParent = fxmlLoader.load(getClass().getResource("adminMainPage.fxml").openStream());
            SceneUtils.showScene(adminMainPageParent, event);
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }

        System.out.println("You clicked me!");
    }

    public void initialize() {
        // TODO
    }
}
