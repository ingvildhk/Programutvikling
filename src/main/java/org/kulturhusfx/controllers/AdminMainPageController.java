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
import org.kulturhusfx.base.*;
import org.kulturhusfx.controllers.uihelpers.InvalidInputHandler;

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

    public void roomRegistrationBtn(ActionEvent event) throws InvalidNumberOfSeatsException, InvalidInputException {
        String room = roomName.getText();
        String type = roomType.getText();
        String seat = totalNumberofSeats.getText();

        //sjekker om noen felt er tomme. Bør kanskje skrive en egen metode for dette i en klasse som kan ta
        //inn ukjent antall parametere
        if (room == null || room.trim().length() == 0 ||
                type == null || type.trim().length() == 0 ||
                seat == null || seat.trim().length() == 0){
            InvalidInputHandler.generateAlert("Alle felt må fylles ut");
            throw new InvalidInputException("Alle felt må fylles ut");
        }
        // Bør ha en else hvor man kun oppretter ny HALL hvis alle felt er fylt ut? Renate
        Hall newHall = new Hall(room, type, seat);
        newHall.checkValidNumberOfSeats(seat);

        /* Tror vi må ha en en metode som skriver hall-object til fil, og så en metode som leser alle hall fra fil og lister
         Hall-objektene i CHoiceBoxen, koden nedenfor er bare meg som tenkter høy
        ChoiceBox<Hall> hallChoiceBox = new ChoiceBox<>();
        for (Hall hall : hallChoiceBox){
            hallChoiceBox.getItems().addAll(Alle Hall fra fil);
        }
        */

        System.out.println(newHall.getHallName() + " " + newHall.getHallType() + " " + newHall.getNumberOfSeats());
    }

    public void eventRegistrationBtn(ActionEvent event) throws InvalidPhoneException, InvalidEmailException, InvalidDateException, InvalidInputException {
        String name = eventName.getText();
        String type = eventType.getAccessibleText();
        String performer = performers.getText();

        // Room er av typen Hall
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
        double ticketPrice2 = Double.parseDouble(ticketPrice.getText());

        // Ikke trim().length() på type eller room da de er dropdown og enten NULL eller noe.
        if (name == null || name.trim().length() == 0 ||
                type == null ||
                performer == null || performer.trim().length() == 0 ||
                room == null ||
                time == null || time.trim().length() == 0 ||
                date == null || date.trim().length() == 0 ||
                program == null || program.trim().length() == 0 ||
                contact == null || contact.trim().length() == 0 ||
                phone == null || phone.trim().length() == 0 ||
                email == null || email.trim().length() == 0){
            InvalidInputHandler.generateAlert("Husk å fylle ut alle obligatoriske felter");
            throw new InvalidInputException("Husk å fylle ut alle obligatoriske felter");
        } else {
            ContactPerson newContactPerson = new ContactPerson(contact, phone, email);
            // newContactPerson.checkValidPhone(phone);
            newContactPerson.checkValidEmail(email);

            // Kontruktøren fungerer ikke da room må være av typen hall
            // Event newEvent = new Event(newContactPerson, name, performer, program, room, date, time, ticketPrice2);

        }

        System.out.println("I work too!");
    }

    public void backToMainPageBtn(ActionEvent event) throws  IOException{
        //this is not working
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

    public void manageEventsBtn(ActionEvent event){

    }

    public void seeAllEventsBtn(ActionEvent event){

    }

    public void initialize() {
        // TODO
    }
}
