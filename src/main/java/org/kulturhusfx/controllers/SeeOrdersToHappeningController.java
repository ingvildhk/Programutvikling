package org.kulturhusfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.*;

public class SeeOrdersToHappeningController {

    private SceneUtils sceneUtils = SceneUtils.getInstance();

    @FXML
    ListView orderListView;

    @FXML
    Label selectedHappeningName;

    private Happening selectedHappening;

    public void initialize () {
    }


    // Method that count the frequency of tickets with same phonenumber and output result in listView
    public void initData (Happening happening){
            this.selectedHappening = happening;

            ObservableList<Ticket> orders = FXCollections.observableArrayList();

            Map <String, Integer> frequencyMap = new HashMap<>();
            for (Ticket ticket : happening.getTicketModel().getTicketList()){
                Integer count = frequencyMap.get(ticket.getPhoneNumber());
                if (count == null)
                    count = 0;

                frequencyMap.put(ticket.getPhoneNumber(), count +1);
                orders.add(ticket);
            }

            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()){
                String order = "Telefonnummer: " + entry.getKey() + " har bestilt " + entry.getValue() + " billett(er).";
                System.out.println(order);
                orderListView.getItems().addAll(order);
            }

        // To set lable to the selected eventName
        selectedHappeningName.setText(selectedHappening.getName());
    }

    public void backToAdminMagageEventsBtn (ActionEvent event) throws IOException {
            sceneUtils.launchScene(event, SeeOrdersToHappeningController.class, "adminManageHappenings.fxml");
    }
}

