package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;
import org.kulturhusfx.util.fileHandling.FileWriterJobj;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
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
    private List<Event> eventList = eventModel.getEventList();
    private Event registeredEvent = eventList.get(eventList.size() - 1);

    public void changeEventBtn(ActionEvent event){
        System.out.println(registeredEvent.toString());
        String type, room, program;
        String name = SceneUtils.changeInformation(changeEventNameTxtField, registeredEventNameLabel);
        String performer = SceneUtils.changeInformation(changeEventPerformersTxtField, registeredEventPerformersLabel);
        String time = SceneUtils.changeInformation(changeEventTimeTxtField, registeredEventTimeLabel);
        String date = SceneUtils.changeInformation(changeEventDateTxtField, registeredEventDateLabel);
        String contact = SceneUtils.changeInformation(changeEventContactPersonTxtField, registeredEventContactPersonLabel);
        String phone = SceneUtils.changeInformation(changeEventPhoneTxtField, registeredEventPhoneLabel);
        String email = SceneUtils.changeInformation(changeEventEmailTxtField, registeredEventEmailLabel);
        String website = SceneUtils.changeInformation(changeEventWebpageTxtField, registeredEventWebpageLabel);
        String firm = SceneUtils.changeInformation(changeEventFirmTxtField, registeredEventFirmLabel);
        String other = SceneUtils.changeInformation(changeEventOtherTxtField, registeredEventOtherLabel);
        String ticket = SceneUtils.changeInformation(changeEventTicketPriceTxtField, registeredEventTicketPriceLabel);

        if(changeEventTypeChoiceBox.getValue() == null){
            type = registeredEventTypeLabel.getText();
        }
        else {
            type = changeEventTypeChoiceBox.getValue().toString();
        }

        if(changeEventHallChoiceBox.getValue() == null){
            room = registeredEventHallLabel.getText();
        }
        else{
            room = changeEventHallChoiceBox.getValue().toString();
        }

        if (changeEventProgramTxtArea.getText() == null || changeEventProgramTxtArea.getText().isEmpty()){
            program = registeredEventScheduleLabel.getText();
        }
        else{
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

        System.out.println(registeredEvent.toString());
        System.out.println(eventList.size());
        setLabels();
        SceneUtils.generateConfirmationAlert("Bekreftelse på registrert arrangement", "Arrangement er registrert");
    }

    public void backToAdminBtn(ActionEvent event){
        SceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }

    public void saveToFileBtn(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();
        System.out.println(fileName);

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                FileWriterJobj jobj = new FileWriterJobj(fileName);
                jobj.saveEventToFile(registeredEvent, fileName);
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveEventToFile(registeredEvent, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
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
        SceneUtils.updateRoomList(changeEventHallChoiceBox, hallModel);
    }

    public void addEventType() {
        SceneUtils.addEventType(changeEventTypeChoiceBox);
    }

    public void initialize() {
        updateRoomList();
        addEventType();
        setLabels();
    }


}
