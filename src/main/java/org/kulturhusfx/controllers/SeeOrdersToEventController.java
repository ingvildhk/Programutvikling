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
import org.kulturhusfx.model.TicketModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SeeOrdersToEventController {

    private SceneUtils sceneUtils = SceneUtils.getInstance();


        @FXML
        TableView<Ticket> tableViewTickets;

        @FXML
        TableColumn<Ticket, String> phoneCol;

        @FXML
        TableColumn<Ticket, Integer> numberOfTicketsCol;


        private Event selectedEvent;
        private int numberOfTickets;

        public void initData (Event event){
            this.selectedEvent = event;

            ObservableList<Ticket> orders = FXCollections.observableArrayList();

            Set<Ticket> liste = new HashSet<>(orders);
            for (Ticket ticket : event.getTicketModel().getTicketList()){
                numberOfTickets = Collections.frequency(orders, ticket.getPhoneNumber());
                numberOfTicketsCol.setCellValueFactory(new PropertyValueFactory<>(Integer.toString(numberOfTickets)));
                orders.add(ticket);
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

        public void initialize () {
            setColumnValues();
        }

        public void backToAdminMagageEventsBtn (ActionEvent event) throws IOException {
            sceneUtils.launchScene(event, SeeOrdersToEventController.class, "adminManageEvents.fxml");
        }

        // TODO Forloop gjennom tickcketModel, +1 nytt tfl
    }

