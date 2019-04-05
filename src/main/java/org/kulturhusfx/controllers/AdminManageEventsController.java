package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class AdminManageEventsController {

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class, "adminMainPage.fxml");
    }
}
