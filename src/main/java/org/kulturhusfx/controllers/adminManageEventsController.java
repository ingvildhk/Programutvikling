package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class adminManageEventsController {

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        Parent manageEventSceneParent = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            manageEventSceneParent = fxmlLoader.load(getClass().getResource("adminManageEvents.fxml").openStream());
        } catch (IOException e){
            e.printStackTrace(); // FXML document should be available
            return;
        }

        Scene manageEventScene = new Scene(manageEventSceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(manageEventScene);
        window.show();

    }
}
