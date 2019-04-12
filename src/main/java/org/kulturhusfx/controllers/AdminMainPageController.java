package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidInputException;

import java.io.File;
import java.io.IOException;
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

    public void initialize() {
        addEventType();
        updateRoomList();
    }

    public void registerFromFileBtn(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg fil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekstfiler", "*.csv", "*.jobj")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        String filePath = selectedFile.getPath();
    }

     public void roomRegistrationBtn(ActionEvent event) {
        registerRoom();
        updateRoomList();
        SceneUtils.launchScene(event, AdminMainPageController.class, "roomRegistrationConfirmationPop.fxml");
    }

    public void registerRoom() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);
        this.hallModel.createHall(room, type, seat);
    }

    public void updateRoomList() {
        SceneUtils.updateRoomList(eventHall, hallModel);
    }

    public void eventRegistrationBtn(ActionEvent event) {
        registerEvent();
        SceneUtils.launchScene(event, EventRegistrationConfirmationPopController.class, "eventRegistrationConfirmationPop.fxml");
    }

    public void registerEvent(){
        //added these as we get a nullpointerexception if there's no input in any fields
        if (eventHall.getValue() == null){
            InvalidInputHandler.generateAlert(
                    new InvalidInputException("Alle felt må fylles ut"));
        }
        if (eventType.getValue() == null){
            InvalidInputHandler.generateAlert(
                    new InvalidInputException("Alle felt må fylles ut"));
        }
        
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
        List<Hall> aList = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = aList.get(hallIndex);

        eventModel.createEvent(contactPerson, name, performer, type, program, hall, date, time, ticket);
    }

    public void addEventType() {
        SceneUtils.addEventType(eventType);
    }

    public void backToMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "MainPage.fxml");
    }

    public void manageEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "adminManageEvents.fxml");
    }

    public void seeAllEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminMainPageController.class, "adminManageHalls.fxml");
    }
}


