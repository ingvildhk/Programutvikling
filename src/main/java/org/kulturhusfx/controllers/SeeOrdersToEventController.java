package org.kulturhusfx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.model.TicketModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.*;

public class SeeOrdersToEventController {

    private SceneUtils sceneUtils = SceneUtils.getInstance();

        @FXML
        TableView <Ticket> tableViewTickets;

        @FXML
        TableColumn<Ticket, String> phoneCol;

        @FXML
        TableColumn<Integer, Integer> numberOfTicketsCol;

        @FXML
        ListView orderListView;

        private Event selectedEvent;
       // private int numberOfTickets;

    public void initialize () {
    }

        public void initData (Event event){
            this.selectedEvent = event;

            ObservableList<Ticket> orders = FXCollections.observableArrayList();

            Map <String, Integer> frequencyMap = new HashMap<>();
            for (Ticket t : event.getTicketModel().getTicketList()){
                Integer count = frequencyMap.get(t.getPhoneNumber());
                if (count == null)
                    count = 0;

                frequencyMap.put(t.getPhoneNumber(), count +1);
                orders.add(t);

            }

            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()){
               // System.out.println(entry.getKey() + ": " + entry.getValue());
                String uttekst = "Telefonnummer: " + entry.getKey() + " har bestilt " + entry.getValue() + " billett(er).";
                System.out.println(uttekst);
               // int numberOfTickets = entry.getValue();
                orderListView.getItems().addAll(uttekst);
            }
        }

        public void backToAdminMagageEventsBtn (ActionEvent event) throws IOException {
            sceneUtils.launchScene(event, SeeOrdersToEventController.class, "adminManageEvents.fxml");
        }
    }

