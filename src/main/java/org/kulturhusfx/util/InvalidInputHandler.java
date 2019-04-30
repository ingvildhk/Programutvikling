package org.kulturhusfx.util;

import javafx.scene.control.Alert;

public class InvalidInputHandler {

    /*'throw exception;' prevents the scene from moving further and is neccessary
    when telling the user what information is wrong or missing when attempting to
    register halls or happenings on the admin page
     */
    public static void generateAlert(RuntimeException exception) {
        alertHelper(exception);
        throw exception;
    }

    //Method without 'throw exception;' for being able to generate alerts from threads
    public static void generateThreadAlert(RuntimeException exception) {
        alertHelper(exception);
    }

    private static void alertHelper(RuntimeException exception){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feil i registreringsdata");
        alert.setHeaderText("Feil i registreringsdata");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }
}