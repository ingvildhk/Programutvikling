package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class PurchaseTicketController {

    public void backToMainPageBtn(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent purchaseTicketSceneParent = fxmlLoader.load(getClass().getResource("MainPage.fxml").openStream());
            SceneUtils.showScene(purchaseTicketSceneParent, event);
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }
    }
}
