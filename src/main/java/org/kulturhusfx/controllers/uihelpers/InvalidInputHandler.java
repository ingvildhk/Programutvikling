package org.kulturhusfx.controllers.uihelpers;

import javafx.scene.control.Alert;

//generell inputhåndteringsklasse - brukes for å sjekke om int blir lagt inn der det skal
//kode tatt fra to vinduer-eksempelet

public class InvalidInputHandler {

    public static void generateAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil persondata");
        alert.setHeaderText("Feil persondata");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
