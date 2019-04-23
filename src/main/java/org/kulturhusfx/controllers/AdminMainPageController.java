package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.ControllerHelper;
import org.kulturhusfx.util.FileChooserMethods;
import org.kulturhusfx.util.SceneUtils;
import java.io.*;
import java.util.List;

public class AdminMainPageController {

    @FXML
    private TextField hallName, hallType, totalNumberOfSeats, performers, eventTime, ticketPrice, eventName;
    @FXML
    private TextField contactName, contactPhone, contactEmail, contactWebsite, contactFirm, contactOther;
    @FXML
    private TextArea eventSchedule;
    @FXML
    private ChoiceBox eventType, eventHall;
    @FXML
    private DatePicker datePicker;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List <Event> eventList = eventModel.getEventList();
    private List <Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();

    public void initialize() {
        addEventType();
        updateHallList();
    }

    public void eventRegistrationBtn(ActionEvent event) {
        registerEvent();
        sceneUtils.launchScene(event, EventRegistrationConfirmationPopController.class, "eventRegistrationConfirmationPop.fxml");
    }

    public void roomRegistrationBtn(ActionEvent event) {
        registerRoom();
        updateHallList();
        sceneUtils.launchScene(event, HallRegistrationConfirmationPopController.class, "hallRegistrationConfirmationPop.fxml");
    }

    public void registerEventFromFileBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        fileChooserMethods.registerEventFromFile();
        updateHallList();
    }

    public void registerHallFromFileBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        fileChooserMethods.registerHallFromFile();
        updateHallList();
    }

    public void backToMainPageBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
    }

    public void manageEventsBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminManageEventsController.class, "adminManageEvents.fxml");
    }

    public void seeAllEventsBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminManageHallsController.class, "adminManageHalls.fxml");
    }

    //Legger til valg i arrangementstype - choicebox
    public void addEventType() {
        ControllerHelper.addEventType(eventType);
    }

    //Legger til saler i salvalg - choicebox
    public void updateHallList() {
        ControllerHelper.updateRoomList(eventHall, hallModel);
    }

    public void registerEvent(){
        //Egen checkmetode for å sjekke om choiceboxer er tomme
        Checker.checkIfChoiceBoxIsEmpty(eventHall);
        Checker.checkIfChoiceBoxIsEmpty(eventType);

        String name = eventName.getText();
        String type = eventType.getValue().toString();
        String performer = performers.getText();
        String room = eventHall.getValue().toString();
        String time = eventTime.getText();
        String date = datePicker.getValue().toString();
        String program = eventSchedule.getText();
        String contact = contactName.getText();
        String phone = contactPhone.getText();
        String email = contactEmail.getText();
        String website = contactWebsite.getText();
        String firm = contactFirm.getText();
        String other = contactOther.getText();
        String ticket = ticketPrice.getText();

        Checker.checkIfFieldIsEmpty(name, type, performer, room, time, program, contact, phone, email, ticket);

        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);

        //Finner hall-objektet ut i fra hallName
        List<Hall> list = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = list.get(hallIndex);

        eventModel.createEvent(contactPerson, name, performer, program, hall, type, date, time, ticket);
    }

    public void registerRoom() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);
        this.hallModel.createHall(room, type, seat);
    }
}


