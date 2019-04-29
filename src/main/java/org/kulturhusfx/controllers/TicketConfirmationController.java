package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.kulturhusfx.base.Happening;

public class TicketConfirmationController {

    //TODO copy managehappeningscontroller?
    private String numberOfTickets = PurchaseTicketController.currentNumberofTickets;
    private Happening currentHappening = MainPageController.currentHappening;

    private double numOfTickets = Double.parseDouble(numberOfTickets);
    private double ticketPrice = Double.parseDouble(currentHappening.getTicketPrice());
    private double totalSum = numOfTickets * ticketPrice;

    @FXML
    Label happeningLabel, totalSumLabel, numberOfTicketsLabel;

    public void initialize() {
        numberOfTicketsLabel.setText(numberOfTickets);
        happeningLabel.setText(currentHappening.getName());
        totalSumLabel.setText(Double.toString(totalSum));
    }

    public void closeWindowBtn(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
