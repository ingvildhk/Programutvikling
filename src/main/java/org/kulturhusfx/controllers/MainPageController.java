package org.kulturhusfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.ContactPersonModel;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    @FXML
    private TableView<Event> tableViewEvents;
    @FXML
    private TableColumn<Event, String> EventColumn, TypeColumn, DateColumn, AvailableColumn;
    @FXML
    private TableColumn<Event, Void> OrderColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List eventList = eventModel.getEventList();

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    /*
    public void setTableView(){
        EventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
    }

    private ObservableList<Event> getEvents(){
        ObservableList<Event> events = FXCollections.observableArrayList();
        events.add(new Event(new ContactPerson("Navn", "12345678", "hei@mail","","", ""), "Arr1",
                "folk", "prog", new Hall("Sal1", "konsert", "123"),"konsert",
                "10/11/11", "10:10","200.00"));
        return events;
    }*/

    public void initialize() {
        // TODO
        //setTableView();
        //tableViewEvents.setItems(getEvents());
        if (hallModel.getHallList().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }
    }
}
