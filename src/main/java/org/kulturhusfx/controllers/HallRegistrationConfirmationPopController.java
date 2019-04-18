package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.FileChooserMethods;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;
import org.kulturhusfx.util.fileHandling.FileWriterJobj;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

import static org.kulturhusfx.util.SceneUtils.generateConfirmationAlert;

public class HallRegistrationConfirmationPopController {

    @FXML
    private Label registeredRoomNameLabel, registeredRoomTypeLabel, registeredRoomNumberSeatsLabel;
    @FXML
    private TextField changeRoomNameTxtField, changeRoomTypeTxtField, changeRoomNumberSeatsTxtField;

    private HallModel hallmodel = HallModel.getInstance();
    private List<Hall> hallList = hallmodel.getHallList();
    private Hall registeredHall = hallList.get(hallList.size()-1);

    public void initialize() {
        setValuetoLabels();
    }

    public void editRoomBtn() {
        editRoom();
        //generateConfirmationAlert("Bekreftelse på registrert sal", "Sal er registrert");
    }

    public void backToAdminMainPage(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, HallRegistrationConfirmationPopController.class, "adminMainPage.fxml");
    }

    public void saveHallBtn(ActionEvent event) throws IOException {
        //Fil blir lagret i samme mappe som repository, vet ikke hvordan man kan endre det
        FileChooserMethods.saveHallToFile(registeredHall);
    }

    // Metoden setter lablene i roomRegistrationConfirmationPop.fxml til verdiene til Hall'en som akkurat er registrert
    public void setValuetoLabels(){
        registeredRoomNameLabel.setText(registeredHall.getHallName());
        registeredRoomTypeLabel.setText(registeredHall.getHallType());
        registeredRoomNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editRoom(){
        String name = SceneUtils.changeInformation(changeRoomNameTxtField, registeredRoomNameLabel);
        String type = SceneUtils.changeInformation(changeRoomTypeTxtField, registeredRoomTypeLabel);
        String seats = SceneUtils.changeInformation(changeRoomNumberSeatsTxtField, registeredRoomNumberSeatsLabel);

        Checker.checkIfHallExcists(name, hallList);
        registeredHall.changeHallInformation(name, type, seats);
        setValuetoLabels();
    }

}
