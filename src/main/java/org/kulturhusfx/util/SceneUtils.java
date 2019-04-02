package org.kulturhusfx.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtils {

    public static void showScene(Parent parent, ActionEvent event) {
        Scene MainPageScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainPageScene);
        window.show();
    }
}
