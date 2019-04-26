package org.kulturhusfx.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.*;
import org.kulturhusfx.util.Threads.CsvEventThread;
import org.kulturhusfx.util.Threads.CsvHallThread;
import org.kulturhusfx.util.Threads.JobjEventThread;
import org.kulturhusfx.util.Threads.JobjHallThread;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminMainPageController {

    @FXML
    private Button btnBack, btnManageHappenings, btnManageHalls, btnRegisterHappening, btnRegisterHappeningFile, btnRegisterHallFile, btnRegisterHall;
    @FXML
    private TextField hallName, hallType, totalNumberOfSeats, performers, time, ticketPrice, happeningName;
    @FXML
    private TextField contactName, contactPhone, contactEmail, contactWebsite, contactFirm, contactOther;
    @FXML
    private TextArea happeningSchedule;
    @FXML
    private ChoiceBox happeningType, happeningHall;
    @FXML
    private DatePicker datePicker;

    private HallModel hallModel = HallModel.getInstance();
    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List <Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooser fileChooser = new FileChooser();
    private FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
    private FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
    private ExecutorService service = Executors.newSingleThreadExecutor();
    //static file for being able to run read from file in threads
    public static File file;

    public void initialize() {
        addHappeningType();
        updateHallList();
        // Setter default value på choiceboxene
        //if test unngår nullpointerexception hvis alle saler er slettet
        if(!hallList.isEmpty()){
            happeningHall.setValue(hallList.get(0).getHallName());
        }
        happeningType.setValue("Konsert");
        datePicker.setValue(ControllerHelper.getLocalDate());

        /*
        Forsøk på å disable passerte datoer, finner ikke helt utav det
        LocalDate date = datePicker.getValue();
        LocalDate today = LocalDate.now();
        if(date.compareTo(today) < 0) {
            boolean invalidDate = true;
            datePicker.setDisable();
        }
        */
    }

    public void happeningRegistrationBtn(ActionEvent event) {
        registerHappening();
        sceneUtils.launchScene(event, HappeningRegistrationConfirmationPopController.class, "happeningRegistrationConfirmationPop.fxml");
    }

    public void roomRegistrationBtn(ActionEvent event) {
        registerRoom();
        updateHallList();
        sceneUtils.launchScene(event, HallRegistrationConfirmationPopController.class, "hallRegistrationConfirmationPop.fxml");
    }

    public void registerHappeningFromFileBtn(ActionEvent event){
        setFileChooserFilters();
        fileChooser.setTitle("Velg arrangementsfil");
        File selectedFile = fileChooser.showOpenDialog(null);
        file = selectedFile;
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            disableButtons();
            Task<Void> task = new CsvEventThread(this::happeningConfirmation);
            service.execute(task);
        }
        else if (fileChooser.getSelectedExtensionFilter() == jobjFilter){
            disableButtons();
            Task<Void> task = new JobjEventThread(this::happeningConfirmation);
            service.execute(task);
        }
    }

    public void happeningConfirmation(){
        openButtons();
        updateHallList();
    }

    public void registerHallFromFileBtn(ActionEvent event) {
        setFileChooserFilters();
        fileChooser.setTitle("Velg salfil");
        File selectedFile = fileChooser.showOpenDialog(null);
        file = selectedFile;
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            disableButtons();
            Task<Void> task = new CsvHallThread(this::hallConfirmation);
            service.execute(task);
        }
        else if (fileChooser.getSelectedExtensionFilter() == jobjFilter){
            disableButtons();
            Task<Void> task = new JobjHallThread(this::hallConfirmation);
            service.execute(task);
        }
    }

    public void hallConfirmation(){
        openButtons();
        updateHallList();
    }

    public void backToMainPageBtn(ActionEvent event) {
        sceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
    }

    public void manageEventsBtn(ActionEvent event) {
        sceneUtils.launchScene(event, AdminManageHappeningsController.class, "adminManageHappenings.fxml");
    }

    public void seeAllEventsBtn(ActionEvent event) {
        sceneUtils.launchScene(event, AdminManageHallsController.class, "adminManageHalls.fxml");
    }

    public void setFileChooserFilters(){
        if(!fileChooser.getExtensionFilters().contains(jobjFilter)){
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
    }

    public void disableButtons(){
        btnBack.setDisable(true);
        btnManageHappenings.setDisable(true);
        btnManageHalls.setDisable(true);
        btnRegisterHappening.setDisable(true);
        btnRegisterHappeningFile.setDisable(true);
        btnRegisterHallFile.setDisable(true);
        btnRegisterHall.setDisable(true);
    }

    public void openButtons(){
        btnBack.setDisable(false);
        btnManageHappenings.setDisable(false);
        btnManageHalls.setDisable(false);
        btnRegisterHappening.setDisable(false);
        btnRegisterHappeningFile.setDisable(false);
        btnRegisterHallFile.setDisable(false);
        btnRegisterHall.setDisable(false);
    }

    //Legger til valg i arrangementstype - choicebox
    public void addHappeningType() {
        ControllerHelper.addHappeningType(happeningType);
    }

    //Legger til saler i salvalg - choicebox
    public void updateHallList() {
        ControllerHelper.updateRoomList(happeningHall, hallModel);
    }

    public void registerHappening(){
        //Egen checkmetode for å sjekke om choiceboxer er tomme
        Checker.checkIfChoiceBoxIsEmpty(happeningHall);
        Checker.checkIfChoiceBoxIsEmpty(happeningType);

        String name = happeningName.getText();
        String type = happeningType.getValue().toString();
        String performer = performers.getText();
        String room = happeningHall.getValue().toString();
        String time = this.time.getText();
        String date = datePicker.getValue().toString();
        String program = happeningSchedule.getText();
        String contact = contactName.getText();
        String phone = contactPhone.getText();
        String email = contactEmail.getText();
        String website = contactWebsite.getText();
        String firm = contactFirm.getText();
        String other = contactOther.getText();
        String ticket = ticketPrice.getText();

        Checker.checkIfFieldIsEmpty(name, type, performer, room, time, program, contact, phone, email, ticket);
        Checker.checkValidPhone(phone);
        Checker.checkValidEmail(email);
        Checker.checkValidDate(date);
        Checker.checkValidTime(time);
        Checker.checkValidTicketPrice(ticket);

        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);

        //Finner room-objektet ut i fra hallName
        List<Hall> list = hallModel.getHallList();
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = list.get(hallIndex);

        happeningModel.createHappening(contactPerson, name, performer, program, hall, type, date, time, ticket);
    }

    public void registerRoom() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        Checker.checkIfFieldIsEmpty(room, type, seat);
        Checker.checkValidNumberOfSeats(seat);
        Checker.checkIfHallExists(room, hallList);
        this.hallModel.createHall(room, type, seat);
    }
}


