package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;

import java.util.List;

import static org.kulturhusfx.util.SceneUtils.generateConfirmationAlert;

public class RoomRegistrationConfirmationPopController {

    @FXML
    Label registeredRoomNameLabel, registeredRoomTypeLabel, registeredRoomNumberSeatsLabel;
    @FXML
    TextField changeRoomNameTxtField, changeRoomTypeTxtField, changeRoomNumberSeatsTxtField;

    private HallModel hallmodel = HallModel.getInstance();
    private List hallList = hallmodel.getHallList();
    private Hall registeredHall = (Hall)hallList.get(hallList.size()-1);

    // Metoden setter lablene i roomRegistrationConfirmationPop.fxml til verdiene til Hall'en som akkurat er registrert
    public void setValuetoLabels(){
        registeredRoomNameLabel.setText(registeredHall.getHallName());
        registeredRoomTypeLabel.setText(registeredHall.getHallType());
        registeredRoomNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editRoomBtn(ActionEvent event) {
        String roomName = changeRoomNameTxtField.getText();
        String roomType = changeRoomTypeTxtField.getText();
        String numberOfSeats = changeRoomNumberSeatsTxtField.getText();
        if(!changeRoomNameTxtField.getText().isEmpty() || !changeRoomTypeTxtField.getText().isEmpty() ||
                !changeRoomNumberSeatsTxtField.getText().isEmpty()){
            registeredHall.changeHallInformation(roomName, roomType, numberOfSeats);
            setValuetoLabels();
        }
        //generateConfirmationAlert();
    }

    public void initialize() {
        setValuetoLabels();

    }






}
