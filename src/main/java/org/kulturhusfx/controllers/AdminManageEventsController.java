package org.kulturhusfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class AdminManageEventsController {

    @FXML
    private TableView<Event> tableViewEvents;

    @FXML
    private TableColumn<Event, String> nameColumn, typeColumn, performersColumn,
         timeColumn, dateColumn, programColumn, priceColumn, contactPersonColumn;

    @FXML
    private  TableColumn<Event, Hall> hallColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();

    public void initialize(){
        setColumnValues();
        setEditableColumns();
    }

    public void deleteEventBtn(){
        deleteEventFromTableView();
    }

    public void seeOrdersToEventbtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class, "seeOrdersToEvent.fxml");
    }

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class, "adminMainPage.fxml");
    }

    // Methods to change and set eventdata i tableView with double-click
    public void editEventNameCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setName(edittedCell.getNewValue().toString());
    }

    public void editEventPerformersCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setPerformers(edittedCell.getNewValue().toString());
    }

    public void editEventTimeCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setTime(edittedCell.getNewValue().toString());
    }

    public void editEventDateCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setDate(edittedCell.getNewValue().toString());
    }

    public void editEventScheduleCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setSchedule(edittedCell.getNewValue().toString());
    }

    public void editEventPriceCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setTicketPrice(edittedCell.getNewValue().toString());
    }

    private void setColumnValues(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        performersColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("performers"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("hall"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("schedule"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("ticketPrice"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("contactPerson"));

        tableViewEvents.setItems(getEvents());
    }

    private void setEditableColumns(){
        // To change text fields, editable must be true
        tableViewEvents.setEditable(true);

        // To make columns editable
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Konsert", "Teater", "Konferanse", "Forestilling", "Annet"));
        performersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        programColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hallColumn.setCellFactory(ComboBoxTableCell.forTableColumn(getHalls()));

        // To select multipe rows with, men fungerer ikke med deleteButton
        tableViewEvents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void deleteEventFromTableView(){
        ObservableList<Event> selectedRows;

        // contains the selected rows
        selectedRows = tableViewEvents.getSelectionModel().getSelectedItems();
        for (Event event : selectedRows){
            eventList.remove(event);
        }
        for(Event events : eventModel.getEventList()) {
            tableViewEvents.getItems().remove(events);
        }
        tableViewEvents.setItems(getEvents());
    }

    // Method to list the registrered events in tableView
    private ObservableList<Event> getEvents(){
        ObservableList<Event> events = FXCollections.observableArrayList();
        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }

    private ObservableList<Hall> getHalls(){
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        for (Hall hall : hallList){
            halls.add(hall);
        }
        return halls;
    }
}
