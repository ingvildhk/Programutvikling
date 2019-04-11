package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.util.SceneUtils;

public class TicketConfirmationPopController {

    private String numberOfTickets = PurchaseTicketController.currentNumberofTickets;
    private Event currentEvent = MainPageController.currentEvent;
    private double numOfTickets = Double.parseDouble(numberOfTickets);
    private double ticketPrice = Double.parseDouble(currentEvent.getTicketPrice());
    private double totalSum = numOfTickets * ticketPrice;

    @FXML
    Label eventLabel, totalSumLabel, numberOfTicketsLabel;

    public void initialize(){
        numberOfTicketsLabel.setText(numberOfTickets);
        eventLabel.setText(currentEvent.getName());
        totalSumLabel.setText(Double.toString(totalSum));
    }

    public void handleBackToMainPageBtn(ActionEvent event){
        SceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
    }
}
