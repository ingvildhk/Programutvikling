package org.kulturhusfx.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.ControllerHelper;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.Threads.CsvEventThread;
import org.kulturhusfx.util.Threads.CsvHallThread;
import org.kulturhusfx.util.Threads.JobjEventThread;
import org.kulturhusfx.util.Threads.JobjHallThread;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.kulturhusfx.util.Checker.exceptionAlertWrapper;

public class AdminMainPageController {

    @FXML
    private Button btnBack, btnManageHappenings, btnManageHalls, btnRegisterHappening, btnRegisterHappeningFile, btnRegisterHallFile, btnRegisterHall;
    @FXML
    private TextField hallName, hallType, totalNumberOfSeats, performers, time, ticketPrice, happeningName,
            contactName, contactPhone, contactEmail, contactWebsite, contactFirm, contactOther;
    @FXML
    private TextArea happeningSchedule;
    @FXML
    private ChoiceBox happeningType, happeningHall;
    @FXML
    private DatePicker datePicker;

    private HallModel hallModel = HallModel.getInstance();
    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List<Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    private FileChooser fileChooser = new FileChooser();
    private FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
    private FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
    private ExecutorService service = Executors.newSingleThreadExecutor();

    //static filename for being able to run read from file in threads
    public static String fileName;

    public void initialize() {
        addHappeningType();
        updateHallList();

        //sets standard choices for choiceboxes and datepicker
        if (!hallList.isEmpty()) {
            happeningHall.setValue(hallList.get(0).getHallName());
        }
        happeningType.setValue("Konsert");
        datePicker.setValue(ControllerHelper.getLocalDate());
    }

    public void happeningRegistrationBtn(ActionEvent event) {
        registerHappening();
        sceneUtils.launchScene(event, HappeningRegistrationConfirmationController.class, "happeningRegistrationConfirmationPop.fxml");
    }

    public void roomRegistrationBtn(ActionEvent event) {
        registerHall();
        updateHallList();
        sceneUtils.launchScene(event, HallRegistrationConfirmationController.class, "hallRegistrationConfirmationPop.fxml");
    }

    public void registerHappeningFromFileBtn(ActionEvent event) {
        setFileChooserFilters();
        fileChooser.setTitle("Velg arrangementsfil");
        File selectedFile = fileChooser.showOpenDialog(null);
        fileName = selectedFile.getName();
        if (fileChooser.getSelectedExtensionFilter() == csvFilter) {
            disableButtons();
            Task<Void> task = new CsvEventThread(this::happeningConfirmation);
            service.execute(task);
        } else if (fileChooser.getSelectedExtensionFilter() == jobjFilter) {
            disableButtons();
            Task<Void> task = new JobjEventThread(this::happeningConfirmation);
            service.execute(task);
        }
    }

    public void happeningConfirmation() {
        openButtons();
        updateHallList();
    }

    public void registerHallFromFileBtn(ActionEvent event) {
        setFileChooserFilters();
        fileChooser.setTitle("Velg salfil");
        File selectedFile = fileChooser.showOpenDialog(null);
        fileName = selectedFile.getName();
        if (fileChooser.getSelectedExtensionFilter() == csvFilter) {
            disableButtons();
            Task<Void> task = new CsvHallThread(this::hallConfirmation);
            service.execute(task);
        } else if (fileChooser.getSelectedExtensionFilter() == jobjFilter) {
            disableButtons();
            Task<Void> task = new JobjHallThread(this::hallConfirmation);
            service.execute(task);
        }
    }

    public void hallConfirmation() {
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

    public void setFileChooserFilters() {
        if (!fileChooser.getExtensionFilters().contains(jobjFilter)) {
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
    }

    public void disableButtons() {
        btnBack.setDisable(true);
        btnManageHappenings.setDisable(true);
        btnManageHalls.setDisable(true);
        btnRegisterHappening.setDisable(true);
        btnRegisterHappeningFile.setDisable(true);
        btnRegisterHallFile.setDisable(true);
        btnRegisterHall.setDisable(true);
    }

    public void openButtons() {
        btnBack.setDisable(false);
        btnManageHappenings.setDisable(false);
        btnManageHalls.setDisable(false);
        btnRegisterHappening.setDisable(false);
        btnRegisterHappeningFile.setDisable(false);
        btnRegisterHallFile.setDisable(false);
        btnRegisterHall.setDisable(false);
    }

    public void addHappeningType() {
        ControllerHelper.addHappeningType(happeningType);
    }

    public void updateHallList() {
        ControllerHelper.updateRoomList(happeningHall, hallModel);
    }

    public void registerHappening() {
        exceptionAlertWrapper(() -> Checker.checkIfChoiceBoxIsEmpty(happeningHall));
        exceptionAlertWrapper(() -> Checker.checkIfChoiceBoxIsEmpty(happeningType));

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

        exceptionAlertWrapper(() -> Checker.checkIfFieldIsEmpty(name, type, performer, room, time, program, contact, phone, email, ticket));
        exceptionAlertWrapper(() -> Checker.checkValidPhone(phone));
        exceptionAlertWrapper(() -> Checker.checkValidEmail(email));
        exceptionAlertWrapper(() -> Checker.checkValidDate(date));
        exceptionAlertWrapper(() -> Checker.checkValidTime(time));
        exceptionAlertWrapper(() -> Checker.checkValidTicketPrice(ticket));

        ContactPerson contactPerson = new ContactPerson(contact, phone, email, website, firm, other);

        //finds the hall object from the hallName
        int hallIndex = hallModel.getHallIndex(room);
        Hall hall = hallList.get(hallIndex);

        happeningModel.createHappening(contactPerson, name, performer, program, hall, type, date, time, ticket);
    }

    public void registerHall() {
        String room = hallName.getText();
        String type = hallType.getText();
        String seat = totalNumberOfSeats.getText();

        exceptionAlertWrapper(() -> Checker.checkIfFieldIsEmpty(room, type, seat));
        exceptionAlertWrapper(() -> Checker.checkValidNumberOfSeats(seat));
        exceptionAlertWrapper(() -> Checker.checkIfHallExists(room, hallList));
        hallModel.createHall(room, type, seat);
    }
}


