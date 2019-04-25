package org.kulturhusfx.util;

import javafx.scene.control.Alert;

import java.io.IOException;

public class FileExceptionHandler {

    public static void generateExceptionmsg(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Filhåndtering feil");
        alert.setHeaderText("Filhåndtering feil");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
    }
}