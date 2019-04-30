package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.*;

import java.util.List;

import static org.kulturhusfx.util.Checker.exceptionAlertWrapper;

public class HallRegistrationConfirmationController {

    @FXML
    private Label registeredHallNameLabel, registeredHallTypeLabel, registeredHallNumberSeatsLabel;
    @FXML
    private TextField changeHallNameTxtField, changeHallTypeTxtField, changeHallNumberSeatsTxtField;

    private HallModel hallmodel = HallModel.getInstance();
    private List<Hall> hallList = hallmodel.getHallList();
    private Hall registeredHall = hallList.get(hallList.size() - 1);
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize() {
        setValuetoLabels();
    }

    //shows information of the newly registered hall
    private void setValuetoLabels() {
        registeredHallNameLabel.setText(registeredHall.getHallName());
        registeredHallTypeLabel.setText(registeredHall.getHallType());
        registeredHallNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editHallBtn() {
        editHall();
        sceneUtils.generateConfirmationAlert("Bekreftelse endring i registrert sal", "Sal er endret");
    }

    private void editHall() {
        String name = ControllerHelper.changeInformation(changeHallNameTxtField, registeredHallNameLabel);
        String type = ControllerHelper.changeInformation(changeHallTypeTxtField, registeredHallTypeLabel);
        String seats = ControllerHelper.changeInformation(changeHallNumberSeatsTxtField, registeredHallNumberSeatsLabel);
        exceptionAlertWrapper(() -> Checker.checkIfHallExists(name, hallList));
        exceptionAlertWrapper(() -> Checker.checkValidNumberOfSeats(seats));
        registeredHall.changeHallInformation(name, type, seats);
        setValuetoLabels();
    }

    public void saveHallBtn() {
        try {
            fileChooserMethods.saveHallToFile(registeredHall);
            sceneUtils.generateConfirmationAlert("Bekreftelse på fillagring", "Sal er lagret til fil");
        } catch (Exception e) {
            FileExceptionHandler.generateExceptionMsg(new Exception("Lagring til fil feilet: " + e.getMessage()));
        }
    }

    public void backToAdminMainPage(ActionEvent event) {
        sceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }
}
