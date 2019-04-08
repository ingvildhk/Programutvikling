package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.ContactPersonModel;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

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
        this.hallModel = HallModel.getInstance();
        this.eventModel = EventModel.getInstance();
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
        List aList = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = (Hall)aList.get(hallIndex);

        eventModel.createEvent(contactPerson, name, performer, type, program, hall, date, time, ticket);
        System.out.println(eventModel.getEventList().get(0));
        System.out.println((eventModel.getEventList().size()));
        SceneUtils.launchScene(event, AdminMainPageController.class, "eventRegistrationConfirmationPop.fxml");
    }

    public void backToMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "MainPage.fxml");
    }

    public void manageEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "adminManageEvents.fxml");
    }

    public void seeAllEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "adminSeeAllEvents.fxml");
    }

    public void updateRoomList() {
        for (Object i : hallModel.getHallList()) {
            Hall hall = (Hall)i;
            String hallName = hall.getHallName();
            System.out.println(hallName);
            if(!eventRoom.getItems().contains(hallName)){
                eventRoom.getItems().add(hallName);
            }
        }
    }

    public void addEventType() {
        eventType.getItems().addAll("Konsert", "Teater");
    }

    public void initialize() {
        addEventType();
        updateRoomList();

    }


}


