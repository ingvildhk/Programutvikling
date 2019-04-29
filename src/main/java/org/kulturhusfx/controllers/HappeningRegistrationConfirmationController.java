package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.util.*;

import java.util.List;

import static org.kulturhusfx.util.Checker.exceptionAlertWrapper;

public class HappeningRegistrationConfirmationController {

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
    private List<Hall> hallList = hallModel.getHallList();
    private Happening registeredHappening = happeningList.get(happeningList.size() - 1);
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();

    public void initialize() {
        setLabels();
        updateRoomList();
        addHappeningType();
    }

    private void setLabels() {
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

    private void updateRoomList() {
        ControllerHelper.updateRoomList(changeHappeningHallChoiceBox, hallModel);
    }

    private void addHappeningType() {
        ControllerHelper.addHappeningType(changeHappeningTypeChoiceBox);
    }

    public void saveToFileBtn(ActionEvent event) {
        try {
            fileChooserMethods.saveHappeningToFile(registeredHappening);
            sceneUtils.generateConfirmationAlert("Bekreftelse på fillagring", "Arrangementet er lagret til fil");

        } catch (Exception e) {
            FileExceptionHandler.generateExceptionMsg(new Exception("Lagring til fil feilet: " + e.getMessage()));
        }
    }

    public void changeHappeningBtn(ActionEvent event) {
        setChangedInformation();
        setLabels();
        sceneUtils.generateConfirmationAlert("Bekreftelse på endring i registrert arrangement", "Arrangement er endret");
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

        exceptionAlertWrapper(() -> Checker.checkValidDate(date));
        exceptionAlertWrapper(() -> Checker.checkValidTime(time));
        exceptionAlertWrapper(() -> Checker.checkValidTicketPrice(ticket));
        exceptionAlertWrapper(() -> Checker.checkValidEmail(email));
        exceptionAlertWrapper(() -> Checker.checkValidPhone(phone));

        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = hallList.get(hallIndex);

        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);

        registeredHappening.changeHappeningInformation(contactPerson, name, performer, type, program, hall, date, time, ticket);
    }

    public void backToAdminBtn(ActionEvent event) {
        sceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }
}