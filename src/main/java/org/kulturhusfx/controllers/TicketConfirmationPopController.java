package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.util.SceneUtils;

public class TicketConfirmationPopController {

   // private SceneUtils sceneUtils = SceneUtils.getInstance();

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

    // Method to close pop-up with button
    public void closeWindowBtn(ActionEvent event){
        //sceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
