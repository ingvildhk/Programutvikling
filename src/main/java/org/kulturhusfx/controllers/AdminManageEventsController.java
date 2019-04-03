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
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent manageEventSceneParent = fxmlLoader.load(getClass().getResource("adminMainPage.fxml").openStream());
            SceneUtils.showScene(manageEventSceneParent, event);
        } catch (IOException e){
            e.printStackTrace(); // FXML document should be available
            return;
        }
    }
}
