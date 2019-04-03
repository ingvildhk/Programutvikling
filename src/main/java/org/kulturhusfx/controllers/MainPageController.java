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
        if(hallModel!=  null) {
            String s = hallModel.getHallMap().toString();
            System.out.println(s);
            label2.setText(s);
        }

    }
}
