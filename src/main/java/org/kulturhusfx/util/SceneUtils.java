package org.kulturhusfx.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;

import java.io.IOException;

public class SceneUtils {

    private static final SceneUtils instance = new SceneUtils();

    private SceneUtils(){
    }

    public static SceneUtils getInstance(){
        return instance;
    }

    // Lage en static metode av denne: Just use TheClassName.class instead of getClass().
    public void launchScene(ActionEvent event, Class myClass, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = fxmlLoader.load(myClass.getResource(fxmlPath).openStream());
            Scene MainPageScene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MainPageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void generateConfirmationAlert(String headerTxt, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bekreftelse");
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);

        alert.showAndWait();
    }
}

