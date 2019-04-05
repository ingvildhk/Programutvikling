package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.model.EventModel;

public class EventRegistrationConfirmationPop {

    @FXML
    Label registeredEventNameLabel, registeredEventTypeLabel, registeredEventPerformersLabel, registeredEventHallLabel;
    @FXML
    Label registeredEventDateLabel, registeredEventTimeLabel, registeredEventTicketPriceLabel, registeredEventScheduleLabel;
    @FXML
    Label registeredEventContactPersonLabel, registeredEventPhoneLabel, registeredEventEmailLabel;
    @FXML
    Label registeredEventWebpageLabel, registeredEventFirmLabel, registeredEventOtherLabel;
    @FXML
    TextField changeEventNameTxtField, changeEventPerformersTxtField, changeEventDateTxtField, changeEventTimeTxtField;
    @FXML
    TextField changeEventTicketPriceTxtField, changeEventContactPersonTxtField, changeEventPhoneTxtField, changeEventEmailTxtField;
    @FXML
    TextField changeEventWebpageTxtField, changeEventFirmTxtField, changeEventOtherTxtField;
    @FXML
    TextArea changeEventProgramTxtArea;
    @FXML
    ChoiceBox changeEventTypeChoiceBox, changeEventHallChoiceBox;

    private EventModel eventModel;

    public EventRegistrationConfirmationPop() {
        this.eventModel = EventModel.getInstance();
    }

    public void changeEventBtn(ActionEvent event) {

    }

    public void initialize() {
        int event = eventModel.getEventList().size();
        System.out.println(event);


    }


}
