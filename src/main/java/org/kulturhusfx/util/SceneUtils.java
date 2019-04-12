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

    public static void showScene(Parent parent, ActionEvent event) {
        Scene MainPageScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MainPageScene);
        window.show();
    }

    // Lage en static metode av denne: Just use TheClassName.class instead of getClass().
    public static void launchScene(ActionEvent event, Class myClass, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent adminMainPageParent = fxmlLoader.load(myClass.getResource(fxmlPath).openStream());
            SceneUtils.showScene(adminMainPageParent, event);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void generateConfirmationAlert(String headerTxt, String contentTxt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bekreftelse");
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);

        alert.showAndWait();
    }

    //////////// OPPRETTE EN NY UTIL-KLASSE FOR DISSE METODENE? /////////////

    //Updates the value of elements of objects when changing information after adding a new hall or event
    public static String changeInformation(TextField textField, Label label){
        String s;
        if (textField.getText() == null || textField.getText().isEmpty()){
            s = label.getText();
        }
        else{
            s = textField.getText();
        }
        return s;
    }


    //Updates the choicebox with the hallnames
    public static void updateRoomList(ChoiceBox box, HallModel hallModel){
        for (Hall hall : hallModel.getHallList()) {
            String hallName = hall.getHallName();
            //makes sure that only unique hallnames are added to the choicebox
            if(!box.getItems().contains(hallName)){
                box.getItems().add(hallName);
            }
        }
    }

    //General method for adding types of events to the choicebox
    public static void addEventType(ChoiceBox box){
        box.getItems().addAll("Konsert", "Teater", "Konferanse", "Forestilling", "Annet");
    }
}

