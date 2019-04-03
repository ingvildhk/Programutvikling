package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class AdminSeeAllEventsController {

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent seeAllEventSceneParent = fxmlLoader.load(getClass().getResource("adminMainPage.fxml").openStream());
            SceneUtils.showScene(seeAllEventSceneParent, event);
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }
    }
}
