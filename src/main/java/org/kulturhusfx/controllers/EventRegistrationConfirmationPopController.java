package org.kulturhusfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.model.ContactPersonModel;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;

import java.util.List;

public class EventRegistrationConfirmationPopController {

    @FXML
    Label registeredEventNameLabel, registeredEventTypeLabel, registeredEventPerformersLabel, registeredEventHallLabel;
    @FXML
    Label registeredEventDateLabel,registeredEventTimeLabel, registeredEventTicketPriceLabel, registeredEventScheduleLabel;
    @FXML
    Label registeredEventContactPersonLabel, registeredEventPhoneLabel, registeredEventEmailLabel, registeredEventWebpageLabel;
    @FXML
    Label registeredEventFirmLabel, registeredEventOtherLabel;

    private HallModel hallModel;
    private EventModel eventModel;
    private ContactPersonModel contactPersonModel;

    public EventRegistrationConfirmationPopController() {
        this.hallModel = HallModel.getInstance();
        this.eventModel = EventModel.getInstance();
        this.contactPersonModel = new ContactPersonModel();
    }

    public void setLabels(){
        List eventList = eventModel.getEventList();
        Event registeredEvent = (Event)eventList.get(eventList.size() - 1);
        registeredEventNameLabel.setText(registeredEvent.getName());
        registeredEventTypeLabel.setText(registeredEvent.getType());
        registeredEventPerformersLabel.setText(registeredEvent.getPerformers());
        registeredEventHallLabel.setText(registeredEvent.getLocation().getHallName());
        registeredEventDateLabel.setText(registeredEvent.getDate());
        registeredEventTimeLabel.setText(registeredEvent.getTime());
        registeredEventTicketPriceLabel.setText(registeredEvent.getTicketPrice());
        registeredEventScheduleLabel.setText(registeredEvent.getSchedule());
        registeredEventContactPersonLabel.setText(registeredEvent.getContactPerson().getName());
        registeredEventPhoneLabel.setText(registeredEvent.getContactPerson().getPhoneNumber());
        registeredEventEmailLabel.setText(registeredEvent.getContactPerson().getEmail());
        registeredEventWebpageLabel.setText(registeredEvent.getContactPerson().getWebpage());
        registeredEventFirmLabel.setText(registeredEvent.getContactPerson().getFirm());
        /* Setting size and text wrap on label
        registeredEventOtherLabel.setWrapText(true);
        registeredEventOtherLabel.setMaxWidth(20.00);*/
        registeredEventOtherLabel.setText(registeredEvent.getContactPerson().getOtherInformation());
    }

    public void initialize() {
        setLabels();
    }


}
