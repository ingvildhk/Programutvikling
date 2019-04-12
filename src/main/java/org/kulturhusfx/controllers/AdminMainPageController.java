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
        updateHallList();
    }

    public void eventRegistrationBtn(ActionEvent event) {
        registerEvent();
        SceneUtils.launchScene(event, EventRegistrationConfirmationPopController.class, "eventRegistrationConfirmationPop.fxml");
    }

    public void roomRegistrationBtn(ActionEvent event) {
        registerRoom();
        updateHallList();
        SceneUtils.launchScene(event, AdminMainPageController.class, "roomRegistrationConfirmationPop.fxml");
    }

    public void registerFromFileBtn(ActionEvent event){
        //Later som dette er kun knapp for å laste inn arrangementer. Tilsvarende lages for sal
        //Metoden er ikke ferdigskrevet enda
        FileChooser fileChooser = newFileChooser("Velg arrangementsfil");
        File selectedFile = fileChooser.showOpenDialog(null);
        String filePath = selectedFile.getPath();
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

    //Legger til valg i arrangementstype - choicebox
    public void addEventType() {
        SceneUtils.addEventType(eventType);
    }

    //Legger til saler i salvalg - choicebox
    public void updateHallList() {
        SceneUtils.updateRoomList(eventHall, hallModel);
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

        //Finner hall-objektet ut ifra på hallName
        List<Hall> list = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = list.get(hallIndex);

        eventModel.createEvent(contactPerson, name, performer, type, program, hall, date, time, ticket);
    }

    public void registerRoom() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);
        this.hallModel.createHall(room, type, seat);
    }

    private FileChooser newFileChooser(String string){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(string);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv eller .jobj", "*.csv", "*.jobj")
        );
        return fileChooser;
    }
}


