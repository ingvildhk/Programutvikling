package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.util.*;

import java.io.IOException;
import java.util.List;

import static org.kulturhusfx.util.Checker.exceptionAlertWrapper;
import static org.kulturhusfx.util.ControllerHelper.changeInformation;

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
    private TextField changeHappeningNameTxtField, changeHappeningPerformersTxtField, changeHappeningDateTxtField, changeHappeningTimeTxtField;
    @FXML
    private TextField changeHappeningTicketPriceTxtField, changeHappeningContactPersonTxtField, changeHappeningPhoneTxtField, changeHappeningEmailTxtField;
    @FXML
    private TextField changeHappeningWebpageTxtField, changeHappeningFirmTxtField, changeHappeningOtherTxtField;
    @FXML
    private TextArea changeHappeningProgramTxtArea;
    @FXML
    private ChoiceBox changeHappeningTypeChoiceBox, changeHappeningHallChoiceBox;

    private HappeningModel happeningModel = HappeningModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();
    private List<Hall> hallList = hallModel.getHallList();
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

        } catch (Exception e) {
            FileExceptionHandler.generateExceptionmsg(new Exception("Lagring til fil feilet: " + e.getMessage()));
        }
    }

    public void updateRoomList() {
        ControllerHelper.updateRoomList(changeHappeningHallChoiceBox, hallModel);
    }

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
        String type, room, program;
        String name = changeInformation(changeHappeningNameTxtField, registeredHappeningNameLabel);
        String performer = changeInformation(changeHappeningPerformersTxtField, registeredHappeningPerformersLabel);
        String time = changeInformation(changeHappeningTimeTxtField, registeredHappeningTimeLabel);
        String date = changeInformation(changeHappeningDateTxtField, registeredHappeningDateLabel);
        String contact = changeInformation(changeHappeningContactPersonTxtField, registeredHappeningContactPersonLabel);
        String phone = changeInformation(changeHappeningPhoneTxtField, registeredHappeningPhoneLabel);
        String email = changeInformation(changeHappeningEmailTxtField, registeredHappeningEmailLabel);
        String website = changeInformation(changeHappeningWebpageTxtField, registeredHappeningWebpageLabel);
        String firm = changeInformation(changeHappeningFirmTxtField, registeredHappeningFirmLabel);
        String other = changeInformation(changeHappeningOtherTxtField, registeredHappeningOtherLabel);
        String ticket = changeInformation(changeHappeningTicketPriceTxtField, registeredHappeningTicketPriceLabel);

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
}