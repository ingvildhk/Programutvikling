package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.*;

import java.io.IOException;
import java.util.List;

public class HallRegistrationConfirmationPopController {

    @FXML
    private Label registeredHallNameLabel, registeredHallTypeLabel, registeredHallNumberSeatsLabel;
    @FXML
    private TextField changeHallNameTxtField, changeHallTypeTxtField, changeHallNumberSeatsTxtField;

    private HallModel hallmodel = HallModel.getInstance();
    private List<Hall> hallList = hallmodel.getHallList();
    private Hall registeredHall = hallList.get(hallList.size()-1);
    private FileChooserMethods fileChooserMethods = FileChooserMethods.getInstance();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize() {
        setValuetoLabels();
    }

    public void editHallBtn() {
        editHall();
        sceneUtils.generateConfirmationAlert("Bekreftelse endring i registrert sal", "Sal er endret");
    }

    public void backToAdminMainPage(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminMainPageController.class, "adminMainPage.fxml");
    }

    public void saveHallBtn(ActionEvent event) throws IOException {
        //Fil blir lagret i samme mappe som repository, vet ikke hvordan man kan endre det
        fileChooserMethods.saveHallToFile(registeredHall);
        sceneUtils.generateConfirmationAlert("Bekreftelse på fillagring", "Sal er lagret til fil");
    }

    // Metoden setter lablene i hallRegistrationConfirmationPop.fxml til verdiene til Hall'en som akkurat er registrert
    public void setValuetoLabels(){
        registeredHallNameLabel.setText(registeredHall.getHallName());
        registeredHallTypeLabel.setText(registeredHall.getHallType());
        registeredHallNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editHall(){
        String name = ControllerHelper.changeInformation(changeHallNameTxtField, registeredHallNameLabel);
        String type = ControllerHelper.changeInformation(changeHallTypeTxtField, registeredHallTypeLabel);
        String seats = ControllerHelper.changeInformation(changeHallNumberSeatsTxtField, registeredHallNumberSeatsLabel);
        Checker.checkIfHallExists(name, hallList);
        Checker.checkValidNumberOfSeats(seats);
        registeredHall.changeHallInformation(name, type, seats);
        setValuetoLabels();
    }

}
