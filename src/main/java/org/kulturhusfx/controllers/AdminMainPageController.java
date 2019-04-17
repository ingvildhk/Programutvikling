package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.FileChooserMethods;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.exception.InvalidInputException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
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
    private List <Event> eventList = eventModel.getEventList();
    private List <Hall> hallList = hallModel.getHallList();

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

    public void registerEventFromFileBtn(ActionEvent event){
        //Metoden er ikke ferdigskrevet enda
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg arrangementsfil");
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getName());
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            createEventsFromCsvFile(selectedFile);
        }
        updateHallList();
    }

    public void registerHallFromFileBtn(ActionEvent event){
        //Metoden er ikke ferdigskrevet enda
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg salfil");
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getName());
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            createHallsFromCsvFile(selectedFile);
        }
        updateHallList();
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

        eventModel.createEvent(contactPerson, name, performer, program, hall, type, date, time, ticket);
    }

    public void registerRoom() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);
        this.hallModel.createHall(room, type, seat);
    }


    //These methods are here as they cannot be refered to from static context - aka not allowed to move
    //them to another class.
    private void createHallsFromCsvFile(File file){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] hallDetails = line.split(",");
                if (hallDetails.length > 0) {
                    for (Hall hall : hallList){
                        if (hall.getHallName() == hallDetails[0]){
                            InvalidInputHandler.generateAlert(new InvalidHallException("En av salene du forsøker å" +
                                    " registrere finnes fra før av: " + hall.getHallName()));
                        }
                    }
                    hallModel.createHall(hallDetails[0], hallDetails[1], hallDetails[2]);
                }
            }
        }
        catch(Exception e)
        {
            InvalidInputHandler.generateAlert(new RuntimeException("Feil i lesing fra fil"));
            e.printStackTrace();
        }
        finally {
            try{
                bufferedReader.close();
            }
            catch (IOException ie){
                InvalidInputHandler.generateAlert(new RuntimeException("Feil oppsto ved lukking av fil"));
                ie.printStackTrace();
            }
        }
    }

    private void createEventsFromCsvFile(File file){

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String [] eventDetails = line.split(",");
                if (eventDetails.length > 0){
                    for (Hall hall : hallList) {
                        if (hall.getHallName() == eventDetails[10]){
                            int hallIndex = hallModel.getHallIndex(eventDetails[10]);
                            Hall aHall = hallList.get(hallIndex);
                            eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                            eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                     aHall, eventDetails[9], eventDetails[13], eventDetails[14], eventDetails[15]);
                        }
                        else {
                            eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                            eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                    new Hall(eventDetails[9], eventDetails[10], eventDetails[11]), eventDetails[12], eventDetails[13],
                                    eventDetails[14], eventDetails[15]);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            InvalidInputHandler.generateAlert(new RuntimeException("Feil i lesing fra fil"));
            e.printStackTrace();
        }
        finally {
            try{
                bufferedReader.close();
            }
            catch (IOException ie){
                InvalidInputHandler.generateAlert(new RuntimeException("Feil oppsto ved lukking av fil"));
            }
        }

    }
}


