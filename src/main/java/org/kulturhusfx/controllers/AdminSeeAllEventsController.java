package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class AdminSeeAllEventsController {

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminSeeAllEventsController.class,  "adminMainPage.fxml");
    }
}
