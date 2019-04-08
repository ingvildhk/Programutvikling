package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;

import java.util.List;

public class RoomRegistrationConfirmationPopController {


    @FXML
    Label registeredRoomNameLabel, registeredRoomTypeLabel, registeredRoomNumberSeatsLabel;
    @FXML
    TextField changeRoomNameTxtField, changeRoomTypeTxtField, changeRoomNumberSeatsTxtField;

    private HallModel hallmodel;

    public RoomRegistrationConfirmationPopController(){
        this.hallmodel = HallModel.getInstance();
    }

    // Metoden setter lablene i roomRegistrationConfirmationPop.fxml til verdiene til Hall'en som akkurat er registrert
    public void setValuetoLabels(){
        List hallList = hallmodel.getHallList();
        Hall registeredHall = (Hall)hallList.get(hallList.size()-1);
        registeredRoomNameLabel.setText(registeredHall.getHallName());
        registeredRoomTypeLabel.setText(registeredHall.getHallType());
        registeredRoomNumberSeatsLabel.setText(registeredHall.getNumberOfSeats());
    }

    public void editRoomBtn() {


    }

    public void initialize() {
        setValuetoLabels();

    }






}
