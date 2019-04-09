package org.kulturhusfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class AdminManageEventsController {

    // Skal den ta inn eventOrder ehr
    @FXML
    private TableView<Event> tableViewEvents;

    @FXML
    private TableColumn<Event, String> nameColumn, typeColumn, performersColumn,
        hallColumn, timeColumn, dateColumn, programColumn, priceColumn, contactPersonColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class, "adminMainPage.fxml");
    }

    // Method to list the registrered events in tableView
    private ObservableList<Event> getEvents(){
        //eventOrder er en ny klasse som kun tar inn den informasjonen vi vil vise på forsiden
        ObservableList<Event> events = FXCollections.observableArrayList();

        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }

     // Funker ikke enda
     public void editEventNameEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        //eventSelected.changeEventInformation(edittedCell.getNewValue());
    }
    
    public void deleteEventBtn(){
        ObservableList<Event> selectedRows;

        // contains the selected rows
        selectedRows = tableViewEvents.getSelectionModel().getSelectedItems();
        for (Event event : selectedRows){
            eventList.remove(event);
            // Updating the tableView when pushing deletebutton
            for(Object obj : eventModel.getEventList()){
                Event events = (Event)obj;
                tableViewEvents.getItems().remove(events);
            }
        }
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        performersColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("performers"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("location"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("schedule"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("ticketPrice"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("contactPerson"));

        tableViewEvents.setItems(getEvents());

        // To change textfields, editable must be true
        tableViewEvents.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // To select multipe rows with, men fungerer ikke med deleteButton
        tableViewEvents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


}
