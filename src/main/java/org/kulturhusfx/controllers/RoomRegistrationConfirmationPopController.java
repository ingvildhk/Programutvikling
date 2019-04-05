package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;

public class RoomRegistrationConfirmationPopController {

    @FXML
    Label registeredRoomNameLabel, registeredRoomTypeLabel, registeredRoomNumberSeatsLabel;
    @FXML
    TextField changeRoomNameTxtField, changeRoomTypeTxtField, changeRoomNumberSeatsTxtField;

    private HallModel hallmodel;

    public RoomRegistrationConfirmationPopController(){

    }

    public void changeRoomBtn() {

    }

    public void setValuetoLabels(){
        //registeredRoomNameLabel.setText(hallmodel.getHallMap().getValue());
    }

    public void initialize() {
        // TODO

    }






}
