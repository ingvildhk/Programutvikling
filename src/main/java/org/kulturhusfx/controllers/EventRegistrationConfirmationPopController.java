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
import org.kulturhusfx.util.SceneUtils;

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
    @FXML
    TextField changeEventNameTxtField, changeEventPerformersTxtField, changeEventDateTxtField, changeEventTimeTxtField;
    @FXML
    TextField changeEventTicketPriceTxtField, changeEventContactPersonTxtField,changeEventPhoneTxtField, changeEventEmailTxtField;
    @FXML
    TextField changeEventWebpageTxtField, changeEventFirmTxtField, changeEventOtherTxtField;
    @FXML
    TextArea changeEventProgramTxtArea;
    @FXML
    ChoiceBox changeEventTypeChoiceBox, changeEventHallChoiceBox;

    private EventModel eventModel = EventModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List eventList = eventModel.getEventList();
    private Event registeredEvent = (Event)eventList.get(eventList.size() - 1);

    public void changeEventBtn(ActionEvent event){
        String name, type, room, performer, time, date, program, contact, phone, email, website, firm, other, ticket;
        name = SceneUtils.changeInformation(changeEventNameTxtField, registeredEventNameLabel);
        if(changeEventTypeChoiceBox.getValue() == null){
            type = registeredEventTypeLabel.getText();
        }
        else {
            type = changeEventTypeChoiceBox.getValue().toString();
        }
        performer = SceneUtils.changeInformation(changeEventPerformersTxtField, registeredEventPerformersLabel);

        if(changeEventHallChoiceBox.getValue() == null){
            room = registeredEventHallLabel.getText();
        }
        else{
            room = changeEventHallChoiceBox.getValue().toString();
        }
        if (changeEventTimeTxtField == null){
            time = registeredEventTimeLabel.getText();
        }
        else{
            time = changeEventTimeTxtField.getText();
        }
        if (changeEventDateTxtField == null){
            date = registeredEventDateLabel.getText();
        }
        else {
            date = changeEventDateTxtField.getText();
        }
        if (changeEventProgramTxtArea == null){
            program = registeredEventScheduleLabel.getText();
        }
        else{
            program = changeEventProgramTxtArea.getText();
        }
        if(changeEventContactPersonTxtField == null){
            contact = registeredEventContactPersonLabel.getText();
        }
        else{
            contact = changeEventContactPersonTxtField.getText();
        }
        if(changeEventPhoneTxtField == null){
            phone = registeredEventPhoneLabel.getText();
        }
        else{
            phone = changeEventPhoneTxtField.getText();
        }
        if(changeEventEmailTxtField == null){
            email = registeredEventEmailLabel.getText();
        }
        else{
            email = changeEventEmailTxtField.getText();
        }
        if(changeEventWebpageTxtField == null){
            website = registeredEventWebpageLabel.getText();
        }
        else{
            website = changeEventWebpageTxtField.getText();
        }
        if(changeEventFirmTxtField == null){
            firm = registeredEventFirmLabel.getText();
        }
        else{
            firm = changeEventFirmTxtField.getText();
        }
        if(changeEventOtherTxtField == null){
            other = registeredEventOtherLabel.getText();
        }
        else{
            other = changeEventOtherTxtField.getText();
        }
        if(changeEventTicketPriceTxtField == null){
            ticket = changeEventTicketPriceTxtField.getText();
        }
        else{
            ticket = changeEventTicketPriceTxtField.getText();
        }

        List aList = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = (Hall)aList.get(hallIndex);
        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);
        registeredEvent.changeEventInformation(contactPerson, name, performer, type, program, hall, date, time, ticket);

        setLabels();


    }

    public void setLabels(){
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

    public void updateRoomList() {
        for (Object i : hallModel.getHallList()) {
            Hall hall = (Hall)i;
            String hallName = hall.getHallName();
            System.out.println(hallName);
            //makes sure that only unique hallnames are added to the choicebox
            if(!changeEventHallChoiceBox.getItems().contains(hallName)){
                changeEventHallChoiceBox.getItems().add(hallName);
            }
        }
    }

    public void addEventType() {
        changeEventTypeChoiceBox.getItems().addAll("Konsert", "Teater");
    }

    public void initialize() {
        updateRoomList();
        addEventType();
        setLabels();
    }


}
