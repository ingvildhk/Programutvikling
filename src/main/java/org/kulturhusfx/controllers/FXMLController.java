package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {

    //@FXML
    //private Label label;

    //Scene change method attempt
    public void handleAdminLoginButtonAction(ActionEvent event) throws IOException {

        /* Får ikke dette til å virke
        Parent scene3Parent = FXMLLoader.load(getClass().getClass().getResource("org/kulturhusfx/adminMainPage.fxml"));
        Scene scene3Scene = new Scene(scene3Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene3Scene);
        window.show();*/

        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("adminMainPage.fxml").openStream());

        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }

        Scene scene = new Scene(root);
        // add CSS etc. should be here
        Stage leggTilArrangement = new Stage();
        leggTilArrangement.setTitle("Programutvikling Semesteroppgave");
        leggTilArrangement.setScene(scene);
        leggTilArrangement.show();

        System.out.println("You clicked me!");
    }

    public void initialize() {
        // TODO
    }
}
