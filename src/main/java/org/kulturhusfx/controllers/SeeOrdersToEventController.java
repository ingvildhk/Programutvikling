package org.kulturhusfx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class SeeOrdersToEventController {

    @FXML
    TableView <Ticket> tableViewTickets;

    @FXML
    TableColumn <Ticket, String> phoneCol, numberOfTicketsCol;


    private Event selectedEvent;
    private int numberOfTickets;


    public void initData(Event event) {
        this.selectedEvent = event;

        ObservableList<Ticket> orders = FXCollections.observableArrayList();


        for (Ticket ticket : event.getTicketModel().getTicketList()){
            if(ticket.getPhoneNumber().equals(ticket) ) {
                // Gir antall billetter som er bestilt på telefonnummer. Må legge til OG equals timePurchased også
                numberOfTickets ++;
               // Add ticket.getPhoneNumber only once
                orders.add(ticket);
            }

        }
        System.out.println(numberOfTickets);


        tableViewTickets.setItems(orders);
    }
        // Sjekke at den henter listen
        //System.out.println(event.getTicketModel().getTicketList().toString());

    public void setColumnValues(){
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
       // numberOfTicketsCol.setCellValueFactory(new PropertyValueFactory<>((numberOfTickets)));
    }

    public void initialize(){
        setColumnValues();
    }

    public void backToAdminMagageEventsBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, SeeOrdersToEventController.class, "adminManageEvents.fxml");
    }

    // TODO Forloop gjennom tickcketModel, +1 nytt tfl
}
