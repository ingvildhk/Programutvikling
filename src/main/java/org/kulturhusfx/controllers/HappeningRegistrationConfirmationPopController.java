package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.*;

import java.io.IOException;
import java.util.List;

public class HappeningRegistrationConfirmationPopController {

    @FXML
    private Label registeredHappeningNameLabel, registeredHappeningTypeLabel, registeredHappeningPerformersLabel, registeredHappeningHallLabel;
    @FXML
    private Label registeredHappeningDateLabel, registeredHappeningTimeLabel, registeredHappeningTicketPriceLabel, registeredHappeningScheduleLabel;
    @FXML
    private Label registeredHappeningContactPersonLabel, registeredHappeningPhoneLabel, registeredHappeningEmailLabel, registeredHappeningWebpageLabel;
    @FXML
    private Label registeredHappeningFirmLabel, registeredHappeningOtherLabel;
    @FXML
    private TextField changeHappeningNameTxtField, changeHappeningPerformersTxtField, changeHappeningTimeTxtField;
    @FXML
    private TextField changeHappeningTicketPriceTxtField, changeHappeningContactPersonTxtField, changeHappeningPhoneTxtField, changeHappeningEmailTxtField;
    @FXML
    private TextField changeHappeningWebpageTxtField, changeHappeningFirmTxtField, changeHappeningOtherTxtField;
    @FXML
    private TextArea changeHappeningProgramTxtArea;
    @FXML
    private ChoiceBox changeHappeningTypeChoiceBox, changeHappeningHallChoiceBox;
    @FXML
    private DatePicker changeHappeningDateDatePicker;

    private HappeningModel happeningModel = HappeningModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();
    private Happening registeredHappening = happeningList.get(happeningList.size() - 1);
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();

    public void initialize() {
        updateRoomList();
        addHappeningType();
        setLabels();
    }

    public void changeHappeningBtn(ActionEvent event) {
        setChangedInformation();
        setLabels();
        sceneUtils.generateConfirmationAlert("Bekreftelse på endring i registrert arrangement", "Arrangement er endret");
    }

    public void backToAdminBtn(ActionEvent event) {
        sceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }

    public void saveToFileBtn(ActionEvent event) {
        try {
            fileChooserMethods.saveHappeningToFile(registeredHappening);
            sceneUtils.generateConfirmationAlert("Bekreftelse på fillagring", "Arrangementet er lagret til fil");

        } catch (IOException e) {
            FileExceptionHandler.generateExceptionmsg(new IOException("Lagring til fil feilet: " + e.getMessage()));
        }
    }

    //Legger til saler i choicebox
    public void updateRoomList() {
        ControllerHelper.updateRoomList(changeHappeningHallChoiceBox, hallModel);
    }

    //Legger til arrangementstyper i choicebox
    public void addHappeningType() {
        ControllerHelper.addHappeningType(changeHappeningTypeChoiceBox);
    }

    public void setLabels() {
        registeredHappeningNameLabel.setText(registeredHappening.getName());
        registeredHappeningTypeLabel.setText(registeredHappening.getType());
        registeredHappeningPerformersLabel.setText(registeredHappening.getPerformers());
        registeredHappeningHallLabel.setText(registeredHappening.getHall().getHallName());
        registeredHappeningDateLabel.setText(registeredHappening.getDate());
        registeredHappeningTimeLabel.setText(registeredHappening.getTime());
        registeredHappeningTicketPriceLabel.setText(registeredHappening.getTicketPrice());
        registeredHappeningScheduleLabel.setText(registeredHappening.getSchedule());
        registeredHappeningContactPersonLabel.setText(registeredHappening.getContactPerson().getContactName());
        registeredHappeningPhoneLabel.setText(registeredHappening.getContactPerson().getPhoneNumber());
        registeredHappeningEmailLabel.setText(registeredHappening.getContactPerson().getEmail());
        registeredHappeningWebpageLabel.setText(registeredHappening.getContactPerson().getWebpage());
        registeredHappeningFirmLabel.setText(registeredHappening.getContactPerson().getFirm());
        registeredHappeningOtherLabel.setText(registeredHappening.getContactPerson().getOtherInformation());
    }

    private void setChangedInformation() {
        String type, room, program, date;
        String name = ControllerHelper.changeInformation(changeHappeningNameTxtField, registeredHappeningNameLabel);
        String performer = ControllerHelper.changeInformation(changeHappeningPerformersTxtField, registeredHappeningPerformersLabel);
        String time = ControllerHelper.changeInformation(changeHappeningTimeTxtField, registeredHappeningTimeLabel);
        //String date = ControllerHelper.changeInformation(changeHappeningDateTxtField, registeredHappeningDateLabel);

        String contact = ControllerHelper.changeInformation(changeHappeningContactPersonTxtField, registeredHappeningContactPersonLabel);
        String phone = ControllerHelper.changeInformation(changeHappeningPhoneTxtField, registeredHappeningPhoneLabel);
        String email = ControllerHelper.changeInformation(changeHappeningEmailTxtField, registeredHappeningEmailLabel);
        String website = ControllerHelper.changeInformation(changeHappeningWebpageTxtField, registeredHappeningWebpageLabel);
        String firm = ControllerHelper.changeInformation(changeHappeningFirmTxtField, registeredHappeningFirmLabel);
        String other = ControllerHelper.changeInformation(changeHappeningOtherTxtField, registeredHappeningOtherLabel);
        String ticket = ControllerHelper.changeInformation(changeHappeningTicketPriceTxtField, registeredHappeningTicketPriceLabel);

        if(changeHappeningDateDatePicker.getValue() == null){
            date = registeredHappeningDateLabel.getText();
        } else{
            date = changeHappeningDateDatePicker.getValue().toString();
        }

        if (changeHappeningTypeChoiceBox.getValue() == null) {
            type = registeredHappeningTypeLabel.getText();
        } else {
            type = changeHappeningTypeChoiceBox.getValue().toString();
        }

        if (changeHappeningHallChoiceBox.getValue() == null) {
            room = registeredHappeningHallLabel.getText();
        } else {
            room = changeHappeningHallChoiceBox.getValue().toString();
        }

        if (changeHappeningProgramTxtArea.getText() == null || changeHappeningProgramTxtArea.getText().isEmpty()) {
            program = registeredHappeningScheduleLabel.getText();
        } else {
            program = changeHappeningProgramTxtArea.getText();
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

        registeredHappening.changeHappeningInformation(contactPerson, name, performer, type, program, hall, date, time, ticket);
    }
}