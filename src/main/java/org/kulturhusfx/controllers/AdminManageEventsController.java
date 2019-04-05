package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class AdminManageEventsController {

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class,  "adminMainPage.fxml");
    }
}
