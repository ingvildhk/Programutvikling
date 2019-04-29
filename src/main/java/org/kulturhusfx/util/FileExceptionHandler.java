package org.kulturhusfx.util;

import javafx.scene.control.Alert;

public class FileExceptionHandler {

    public static void generateExceptionMsg(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Filhåndtering feil");
        alert.setHeaderText("Filhåndtering feil");
        alert.setContentText(exception.getMessage());

        alert.showAndWait();
    }
}