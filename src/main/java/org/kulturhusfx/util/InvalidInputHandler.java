package org.kulturhusfx.util;

import javafx.scene.control.Alert;

public class InvalidInputHandler {

    public static void generateAlert(RuntimeException exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil i registreringsdata");
        alert.setHeaderText("Feil i registreringsdata");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
        throw exception; // Hvilket Exception?

    }

    //method without "throw exception" for being able to generate alerts from threads
    public static void generateThreadAlert(RuntimeException exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil i registreringsdata");
        alert.setHeaderText("Feil i registreringsdata");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
    }
}