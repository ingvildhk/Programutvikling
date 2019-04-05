package org.kulturhusfx.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.ContactPersonModel;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.InvalidInputHandler;
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
    private EventModel eventModel;
    private ContactPersonModel contactPersonModel;

    public AdminMainPageController() {
        this.hallModel = new HallModel();
        this.eventModel = new EventModel();
        this.contactPersonModel = new ContactPersonModel();

    }

    public void roomRegistrationBtn(ActionEvent event) {
        String room = roomName.getText();
        String type = roomType.getText();
        String seat = totalNumberofSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);

        this.hallModel.createHall(room, type, seat);
        updateRoomList();
    }

    public void eventRegistrationBtn(ActionEvent event) {
        String name = eventName.getText();
        String type = eventType.getValue().toString();
        String performer = performers.getText();
        String room = eventRoom.getValue().toString();
        String time = eventTime.getText();
        String date = eventDate.getText();
        String program = eventProgram.getText();
        String contact = contactName.getText();
        String phone = contactPhone.getText();
        String email = contactEmail.getText();
        String website = contactWebsite.getText();
        String firm = contactFirm.getText();
        String other = contactOther.getText();
        String ticket = ticketPrice.getText();

        Checker.checkIfFieldIsEmpty(name, type, performer, room, time, date, program, contact, phone, email, ticket);
        // Room er av typen Hall
        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);
        Hall hall = (Hall)hallModel.getHallMap().get(room);
        eventModel.createEvent(contactPerson, name, performer, type, program, hall, date, time, ticket);
        System.out.println(type);
        System.out.println(eventModel.getEventList().toString());
       // String name, String phoneNumber,
          //      String email, String webpage, String firm, String otherInformation
            //System.out.println(hallModel.halls.toString());

        }


        public void backToMainPageBtn (ActionEvent event) throws IOException {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent mainPageParent = fxmlLoader.load(getClass().getResource("MainPage.fxml").openStream());
                SceneUtils.showScene(mainPageParent, event);
            } catch (IOException e) {
                e.printStackTrace(); // FXML document should be available
                return;
            }
        }

        public void manageEventsBtn (ActionEvent event) throws IOException {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent manageEventSceneParent = fxmlLoader.load(getClass().getResource("adminManageEvents.fxml").openStream());
                SceneUtils.showScene(manageEventSceneParent, event);
            } catch (IOException e) {
                e.printStackTrace(); // FXML document should be available
            }
        }

        public void seeAllEventsBtn (ActionEvent event) throws IOException {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent seeAllEventSceneParent = fxmlLoader.load(getClass().getResource("adminSeeAllEvents.fxml").openStream());
                SceneUtils.showScene(seeAllEventSceneParent, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateRoomList(){
            for(String i : HallModel.hallMap.keySet()){
                eventRoom.getItems().add(i);
            }
        }

        public void addEventType(){
            eventType.getItems().addAll("Konsert", "Teater");
        }

        public void initialize () {
            addEventType();
            updateRoomList();

        }


    }


