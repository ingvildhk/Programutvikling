package org.kulturhusfx.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;


public class AdminMainPageController{

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
        // Bør ha en else hvor man kun oppretter ny HALL hvis alle felt er fylt ut? Renate

        /* Tror vi må ha en en metode som skriver hall-object til fil, og så en metode som leser alle hall fra fil og lister
         Hall-objektene i CHoiceBoxen, koden nedenfor er bare meg som tenkter høy
        ChoiceBox<Hall> hallChoiceBox = new ChoiceBox<>();
        for (Hall hall : hallChoiceBox){
            hallChoiceBox.getItems().addAll(Alle Hall fra fil);
        }
        */

        this.hallModel.createHall(room, type, seat);

        System.out.println(hallModel.toString());

    }

    public void eventRegistrationBtn(ActionEvent event) {
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
            InvalidInputHandler.generateAlert(new InvalidInputException("Husk å fylle ut alle obligatoriske felter"));
        } else {
            ContactPerson newContactPerson = new ContactPerson(contact, phone, email, website, firm, other);
            // newContactPerson.checkValidPhone(phone);
            Checker.checkValidEmail(email);

            // Kontruktøren fungerer ikke da room må være av typen hall
            // Event newEvent = new Event(newContactPerson, name, performer, program, room, date, time, ticketPrice2);

        }
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

    public void manageEventsBtn(ActionEvent event) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent manageEventSceneParent = fxmlLoader.load(getClass().getResource("adminManageEvents.fxml").openStream());
            SceneUtils.showScene(manageEventSceneParent, event);
        } catch (IOException e){
            e.printStackTrace(); // FXML document should be available
        }
    }

    public void seeAllEventsBtn(ActionEvent event) throws IOException{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent seeAllEventSceneParent = fxmlLoader.load(getClass().getResource("adminSeeAllEvents.fxml").openStream());
            SceneUtils.showScene(seeAllEventSceneParent, event);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initialize() {
        // TODO
    }
}
