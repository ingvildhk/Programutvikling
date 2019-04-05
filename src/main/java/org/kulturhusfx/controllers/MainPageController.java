package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.ContactPersonModel;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class MainPageController {

    @FXML
    public Label label2;

    private HallModel hallModel;
    private EventModel eventModel;
    private ContactPersonModel contactPersonModel;

    public MainPageController() {
        this.hallModel = new HallModel();
        this.eventModel = new EventModel();
        this.contactPersonModel = new ContactPersonModel();
    }


    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, MainPageController.class,  "adminMainPage.fxml");
    }

    public void initialize() {
        // TODO
        if (hallModel.getHallMap().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }
    }
}
