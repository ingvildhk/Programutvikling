package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.util.List;

public class PurchaseTicketController {

    @FXML
    Label eventLabel, hallLabel, performersLabel, dateLabel, timeLabel, typeLabel, scheduleLabel, ticketPriceLabel, sumLabel;
    @FXML
    TextField phoneTxtField;
    @FXML
    ChoiceBox numberOfTicketsChoiceBox;

    private EventModel eventModel = EventModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();


    public void orderBtn(ActionEvent event){

    }

    public void backToMainPageBtn(ActionEvent event) {
        SceneUtils.launchScene(event, MainPageController.class,"MainPage.fxml" );
    }
}
