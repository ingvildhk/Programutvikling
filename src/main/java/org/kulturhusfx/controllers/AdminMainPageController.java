package org.kulturhusfx.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kulturhusfx.base.exception.InvalidInputException;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

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

    private HallModel hallModel;

    public AdminMainPageController() {
        this.hallModel = new HallModel();
    }

    public void roomRegistrationBtn(ActionEvent event) {
        String room = roomName.getText();
        String type = roomType.getText();
        String seat = totalNumberofSeats.getText();

        //sjekker om noen felt er tomme. Bør kanskje skrive en egen metode for dette i en klasse som kan ta
        //inn ukjent antall parametere
        if (room == null || room.trim().length() == 0 ||
                type == null || type.trim().length() == 0 ||
                seat == null || seat.trim().length() == 0){
            InvalidInputHandler.generateAlert(new InvalidInputException("Alle felt må fylles ut"));
        }

        this.hallModel.createHall(room, type, seat);

        System.out.println(hallModel.halls.toString());

    }

    public void eventRegistrationBtn(ActionEvent event) {
        String name = eventName.getText();
        String type = eventType.getAccessibleText();
        String performer = performers.getText();
        String room = eventRoom.getAccessibleText();
        String time = eventTime.getText();
        String date = eventDate.getText();
        String program = eventProgram.getText();
        String contact = contactName.getText();
        String phone = contactPhone.getText();
        String email = contactEmail.getText();
        String website = contactWebsite.getText();
        String firm = contactFirm.getText();
        String other = contactOther.getText();

        System.out.println("I work too!");
    }

    public void backToMainPageBtn(ActionEvent event) throws  IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent mainPageParent = fxmlLoader.load(getClass().getResource("MainPage.fxml").openStream());
            SceneUtils.showScene(mainPageParent, event);
        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }
    }

    public void manageEventsBtn(ActionEvent event){

    }

    public void seeAllEventsBtn(ActionEvent event){

    }

    public void initialize() {
        // TODO
    }
}
