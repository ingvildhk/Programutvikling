package org.kulturhusfx.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtils {

    public static void showScene(Parent parent, ActionEvent event) {
        Scene MainPageScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainPageScene);
        window.show();
    }

    // Lage en static metode av denne: Just use TheClassName.class instead of getClass().
    public static void launchScene(ActionEvent event, Class myClass, String fxmlPath){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent adminMainPageParent = fxmlLoader.load(myClass.getResource(fxmlPath).openStream());
            SceneUtils.showScene(adminMainPageParent, event);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }



}
