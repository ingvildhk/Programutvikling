package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class SeeOrdersToEventController {

    public void backToAdminMagageEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, SeeOrdersToEventController.class, "adminManageEvents.fxml");
    }

    // TODO Forloop gjennom tickcketModel, +1 nytt tfl

    public void initialize(){

    }
}
