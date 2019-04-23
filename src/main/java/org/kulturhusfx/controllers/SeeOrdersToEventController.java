package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class SeeOrdersToEventController {

    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize(){

    }

    public void backToAdminMagageEventsBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, SeeOrdersToEventController.class, "adminManageEvents.fxml");
    }

    // TODO Forloop gjennom tickcketModel, +1 nytt tfl
}
