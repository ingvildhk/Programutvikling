package org.kulturhusfx.util;

import javafx.scene.control.Alert;

//generell inputhåndteringsklasse - brukes for å sjekke om int blir lagt inn der det skal
//kode tatt fra to vinduer-eksempelet

public class InvalidInputHandler {

    public static void generateAlert(RuntimeException exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil i registreringsdata");
        alert.setHeaderText("Feil i registreringsdata");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
        throw exception; // Hvilket Exception?

    }

    /*throw exception gjør at tråden fryser, men throw exception sørger også for at programmet stopper
    når bruker skriver inn feil i gui. Trenger derfor to metoder, en med throw og en uten
     */
    public static void generateThreadAlert(RuntimeException exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil i registreringsdata");
        alert.setHeaderText("Feil i registreringsdata");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
    }
}