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
    private ListView <String> orderListView;

    @FXML
    private Label selectedHappeningName;

    public void initialize () {}

    /* Method that receive the selected happening-object from AdminManageHappeningsController and
     count the frequency of tickets based on phone number and outputs result in listView
     */
    public void viewOrdersToSelectedEvent(Happening happening){

        ObservableList<Ticket> orders = FXCollections.observableArrayList();

            Map <String, Integer> map = new HashMap<>();
            for (Ticket ticket : happening.getTicketModel().getTicketList()){
                Integer repetition = map.get(ticket.getPhoneNumber());
                if (repetition == null){
                    repetition = 0;
                }

                map.put(ticket.getPhoneNumber(), repetition +1);
                orders.add(ticket);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()){
                String order = "Telefonnummer: " + entry.getKey() + " har bestilt " + entry.getValue() + " billett(er).";
                orderListView.getItems().addAll(order);
            }

        // To set label selectedHappeningName, to the selected happeningName
        selectedHappeningName.setText("'" + happening.getName() + "'");
    }

    public void backToAdminManageEventsBtn(ActionEvent event) throws IOException {
            sceneUtils.launchScene(event, SeeOrdersToHappeningController.class, "adminManageHappenings.fxml");
    }
}

