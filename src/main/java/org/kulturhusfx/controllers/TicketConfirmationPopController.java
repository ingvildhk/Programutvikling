package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.kulturhusfx.base.Happening;

public class TicketConfirmationPopController {

   // private SceneUtils sceneUtils = SceneUtils.getInstance();

    private String numberOfTickets = PurchaseTicketController.currentNumberofTickets;
    private Happening currentHappening = MainPageController.currentHappening;
    private double numOfTickets = Double.parseDouble(numberOfTickets);
    private double ticketPrice = Double.parseDouble(currentHappening.getTicketPrice());
    private double totalSum = numOfTickets * ticketPrice;

    @FXML
    Label happeningLabel, totalSumLabel, numberOfTicketsLabel;

    public void initialize(){
        numberOfTicketsLabel.setText(numberOfTickets);
        happeningLabel.setText(currentHappening.getName());
        totalSumLabel.setText(Double.toString(totalSum));
    }

    // Method to close window with button
    public void closeWindowBtn(ActionEvent event){
        //sceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
