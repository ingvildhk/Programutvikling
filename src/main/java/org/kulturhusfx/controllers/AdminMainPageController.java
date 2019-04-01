package org.kulturhusfx.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.InvalidNumberOfSeatsException;

import java.io.IOException;

public class AdminMainPageController {

    @FXML
    TextField roomName, roomType, totalNumberofSeats, performers, eventTime, ticketPrice, eventName;
    @FXML
    TextField contactName, contactPhone, contactEmail, contactWebsite, contactFirm, contactOther, eventDate;
    @FXML
    TextArea eventProgram;
    @FXML
    ChoiceBox eventType, eventRoom;

    public void roomRegistrationBtn(ActionEvent event) throws InvalidNumberOfSeatsException {
        String room = roomName.getText();
        String type = roomType.getText();
        String seat = totalNumberofSeats.getText();
        Hall newHall = new Hall(room, type, seat);
        newHall.checkValidNumberOfSeats(seat);

        System.out.println(newHall.getHallName() + newHall.getHallType() + newHall.getNumberOfSeats());
    }

    public void backToMainPageBtn(ActionEvent event) throws  IOException{
        Parent MainPageParent = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            MainPageParent = fxmlLoader.load(getClass().getResource("MainPage.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }

        /*
        Scene MainPageScene = new Scene(MainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainPageScene);
        window.show();*/

    }

    public void eventRegistrationBtn(ActionEvent event) throws IOException {
        System.out.println("I work too!");
    }

    public void manageEventsBtn(ActionEvent event){

    }

    public void seeAllEventsBtn(ActionEvent event){

    }

    public void initialize() {
        // TODO
    }
}
