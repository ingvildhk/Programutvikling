package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.*;

import java.io.IOException;
import java.util.List;

public class EventRegistrationConfirmationPopController {

    @FXML
    private Label registeredEventNameLabel, registeredEventTypeLabel, registeredEventPerformersLabel, registeredEventHallLabel;
    @FXML
    private Label registeredEventDateLabel, registeredEventTimeLabel, registeredEventTicketPriceLabel, registeredEventScheduleLabel;
    @FXML
    private Label registeredEventContactPersonLabel, registeredEventPhoneLabel, registeredEventEmailLabel, registeredEventWebpageLabel;
    @FXML
    private Label registeredEventFirmLabel, registeredEventOtherLabel;
    @FXML
    private TextField changeEventNameTxtField, changeEventPerformersTxtField, changeEventDateTxtField, changeEventTimeTxtField;
    @FXML
    private TextField changeEventTicketPriceTxtField, changeEventContactPersonTxtField, changeEventPhoneTxtField, changeEventEmailTxtField;
    @FXML
    private TextField changeEventWebpageTxtField, changeEventFirmTxtField, changeEventOtherTxtField;
    @FXML
    private TextArea changeEventProgramTxtArea;
    @FXML
    private ChoiceBox changeEventTypeChoiceBox, changeEventHallChoiceBox;

    private EventModel eventModel = EventModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private Event registeredEvent = eventList.get(eventList.size() - 1);
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();

    public void initialize() {
        updateRoomList();
        addEventType();
        setLabels();
    }

    public void changeEventBtn(ActionEvent event) {
        setChangedInformation();
        setLabels();
        sceneUtils.generateConfirmationAlert("Bekreftelse på endring i registrert arrangement", "Arrangement er endret");
    }

    public void backToAdminBtn(ActionEvent event) {
        sceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }

    public void saveToFileBtn(ActionEvent event) {
        try {
            fileChooserMethods.saveEventToFile(registeredEvent);
            sceneUtils.generateConfirmationAlert("Bekreftelse på fillagring", "Arrangementet er lagret til fil");

        } catch (IOException e) {
            FileExceptionHandler.generateExceptionmsg(new IOException("Lagring til fil feilet: " + e.getMessage()));
        }
    }

    //Legger til saler i choicebox
    public void updateRoomList() {
        ControllerHelper.updateRoomList(changeEventHallChoiceBox, hallModel);
    }

    //Legger til arrangementstyper i choicebox
    public void addEventType() {
        ControllerHelper.addEventType(changeEventTypeChoiceBox);
    }

    public void setLabels() {
        registeredEventNameLabel.setText(registeredEvent.getName());
        registeredEventTypeLabel.setText(registeredEvent.getType());
        registeredEventPerformersLabel.setText(registeredEvent.getPerformers());
        registeredEventHallLabel.setText(registeredEvent.getHall().getHallName());
        registeredEventDateLabel.setText(registeredEvent.getDate());
        registeredEventTimeLabel.setText(registeredEvent.getTime());
        registeredEventTicketPriceLabel.setText(registeredEvent.getTicketPrice());
        registeredEventScheduleLabel.setText(registeredEvent.getSchedule());
        registeredEventContactPersonLabel.setText(registeredEvent.getContactPerson().getContactName());
        registeredEventPhoneLabel.setText(registeredEvent.getContactPerson().getPhoneNumber());
        registeredEventEmailLabel.setText(registeredEvent.getContactPerson().getEmail());
        registeredEventWebpageLabel.setText(registeredEvent.getContactPerson().getWebpage());
        registeredEventFirmLabel.setText(registeredEvent.getContactPerson().getFirm());
        registeredEventOtherLabel.setText(registeredEvent.getContactPerson().getOtherInformation());
    }

    private void setChangedInformation() {
        String type, room, program;
        String name = ControllerHelper.changeInformation(changeEventNameTxtField, registeredEventNameLabel);
        String performer = ControllerHelper.changeInformation(changeEventPerformersTxtField, registeredEventPerformersLabel);
        String time = ControllerHelper.changeInformation(changeEventTimeTxtField, registeredEventTimeLabel);
        String date = ControllerHelper.changeInformation(changeEventDateTxtField, registeredEventDateLabel);
        String contact = ControllerHelper.changeInformation(changeEventContactPersonTxtField, registeredEventContactPersonLabel);
        String phone = ControllerHelper.changeInformation(changeEventPhoneTxtField, registeredEventPhoneLabel);
        String email = ControllerHelper.changeInformation(changeEventEmailTxtField, registeredEventEmailLabel);
        String website = ControllerHelper.changeInformation(changeEventWebpageTxtField, registeredEventWebpageLabel);
        String firm = ControllerHelper.changeInformation(changeEventFirmTxtField, registeredEventFirmLabel);
        String other = ControllerHelper.changeInformation(changeEventOtherTxtField, registeredEventOtherLabel);
        String ticket = ControllerHelper.changeInformation(changeEventTicketPriceTxtField, registeredEventTicketPriceLabel);

        if (changeEventTypeChoiceBox.getValue() == null) {
            type = registeredEventTypeLabel.getText();
        } else {
            type = changeEventTypeChoiceBox.getValue().toString();
        }

        if (changeEventHallChoiceBox.getValue() == null) {
            room = registeredEventHallLabel.getText();
        } else {
            room = changeEventHallChoiceBox.getValue().toString();
        }

        if (changeEventProgramTxtArea.getText() == null || changeEventProgramTxtArea.getText().isEmpty()) {
            program = registeredEventScheduleLabel.getText();
        } else {
            program = changeEventProgramTxtArea.getText();
        }

        Checker.checkValidDate(date);
        Checker.checkValidTime(time);
        Checker.checkValidTicketPrice(ticket);
        Checker.checkValidEmail(email);
        Checker.checkValidPhone(phone);

        List<Hall> aList = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = aList.get(hallIndex);

        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);

        registeredEvent.changeEventInformation(contactPerson, name, performer, type, program, hall, date, time, ticket);
    }
}