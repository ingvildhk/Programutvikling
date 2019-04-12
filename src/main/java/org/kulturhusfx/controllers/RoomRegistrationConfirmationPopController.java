package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.kulturhusfx.util.SceneUtils.generateConfirmationAlert;

public class RoomRegistrationConfirmationPopController {

    @FXML
    Label registeredRoomNameLabel, registeredRoomTypeLabel, registeredRoomNumberSeatsLabel;
    @FXML
    TextField changeRoomNameTxtField, changeRoomTypeTxtField, changeRoomNumberSeatsTxtField;

    private HallModel hallmodel = HallModel.getInstance();
    private List<Hall> hallList = hallmodel.getHallList();
    private Hall registeredHall = hallList.get(hallList.size()-1);

    // Metoden setter lablene i roomRegistrationConfirmationPop.fxml til verdiene til Hall'en som akkurat er registrert
    public void setValuetoLabels(){
        registeredRoomNameLabel.setText(registeredHall.getHallName());
        registeredRoomTypeLabel.setText(registeredHall.getHallType());
        registeredRoomNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editRoomBtn() {
        editRoom();
        generateConfirmationAlert();
    }

    public void editRoom(){
        String name = SceneUtils.changeInformation(changeRoomNameTxtField, registeredRoomNameLabel);
        String type = SceneUtils.changeInformation(changeRoomTypeTxtField, registeredRoomTypeLabel);
        String seats = SceneUtils.changeInformation(changeRoomNumberSeatsTxtField, registeredRoomNumberSeatsLabel);

        registeredHall.changeHallInformation(name, type, seats);
        setValuetoLabels();
    }

    public void backToAdminMainPage(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, RoomRegistrationConfirmationPopController.class, "adminMainPage.fxml");
    }

    public void saveHallBtn(ActionEvent event) throws IOException {
        //Fil blir lagret i samme mappe som repository, vet ikke hvordan man kan endre det
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Jobj eller csv", "*.jobj", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {

            FileWriterCsv csv = new FileWriterCsv(fileName);
            csv.saveHallCsv(registeredHall.getHallName(), registeredHall.getHallType(), registeredHall.getNumberOfSeats(), fileName);
        }
    }

    public void initialize() {
        setValuetoLabels();
    }






}
