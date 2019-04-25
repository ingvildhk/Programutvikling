package org.kulturhusfx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.SceneUtils;
import java.io.IOException;
import java.util.List;

public class AdminManageEventsController {


    @FXML
    private TableView<Event> tableViewEvents;

    @FXML
    private TableColumn<Event, String> nameColumn, typeColumn, performersColumn,
            timeColumn, dateColumn, programColumn, priceColumn, contactNameCol,
            contactFirmCol, contactWebsiteCol, contactPhoneCol, contactEmailCol, contactOtherCol;

    @FXML
    private  TableColumn<Event, Hall> hallColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();


    public void initialize() {
        setColumnValues();
        setEditableColumns();
    }

    // Method to list the registrered events in tableView
    private ObservableList<Event> getEvents(){
        ObservableList<Event> events = FXCollections.observableArrayList();
        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }

    public void deleteEventBtn(){
        deleteEventFromTableView();
    }

    // Test om det fungerte med egen metode
    public void showScene(Parent parent, ActionEvent event) {
        Scene MainPageScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MainPageScene);
        window.show();
    }

    public void seeOrdersToEventbtn(ActionEvent event) throws IOException {

       // sceneUtils.launchScene(event, AdminManageEventsController.class, "seeOrdersToEvent.fxml");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("seeOrdersToEvent.fxml"));
        Parent parent = loader.load();

        showScene(parent, event);

        /*
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        */

        SeeOrdersToEventController controller = loader.getController();

        // Access the controller and call a method
        controller.initData(tableViewEvents.getSelectionModel().getSelectedItem());

    }

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminManageEventsController.class, "adminMainPage.fxml");
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
        Checker.checkValidTime(edittedCell.getNewValue().toString());
        eventSelected.setTime(edittedCell.getNewValue().toString());
    }

    public void editEventDateCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        Checker.checkValidDate(edittedCell.getNewValue().toString());
        eventSelected.setDate(edittedCell.getNewValue().toString());
    }

    public void editEventScheduleCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setSchedule(edittedCell.getNewValue().toString());
    }

    public void editEventPriceCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        Checker.checkValidTicketPrice(edittedCell.getNewValue().toString());
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

        contactNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getContactName()));
        contactPhoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getPhoneNumber()));
        contactEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getEmail()));
        contactFirmCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getFirm()));
        contactWebsiteCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getWebpage()));
        contactOtherCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().getOtherInformation()));

        tableViewEvents.setItems(getEvents());
    }

    private void setEditableColumns() {
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
        //hallColumn.setCellFactory(ComboBoxTableCell.forTableColumn(getHalls()));
        hallColumn.setCellFactory(ComboBoxTableCell.forTableColumn(getHalls()));

        contactNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactWebsiteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactFirmCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactOtherCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // To select multipe rows with, men fungerer ikke med deleteButton
        tableViewEvents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void editEventTypeCellEvent(TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setType(edittedCell.getNewValue().toString());
    }

    public void editEventHallCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setHall((Hall)edittedCell.getNewValue());
    }

    public void editEventContactNameCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setContactPersonName(edittedCell.getNewValue().toString());
    }

    public void editEventContactPhoneCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        Checker.checkValidPhone(edittedCell.getNewValue().toString());
        eventSelected.setContactPersonPhone(edittedCell.getNewValue().toString());
    }

    public void editEventContactEmailCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        Checker.checkValidEmail(edittedCell.getNewValue().toString());
        eventSelected.setContactPersonEmail(edittedCell.getNewValue().toString());
    }

    public void editEventContactWebpageCellEvent (TableColumn.CellEditEvent edittedCell) {
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setContactPersonWebpage(edittedCell.getNewValue().toString());
    }

    public void editEventContactFirmCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setContactPersonFirm(edittedCell.getNewValue().toString());
    }

    public void editEventContactOtherCellEvent (TableColumn.CellEditEvent edittedCell){
        Event eventSelected = tableViewEvents.getSelectionModel().getSelectedItem();
        eventSelected.setContactPersonOther(edittedCell.getNewValue().toString());
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


    private ObservableList<Hall> getHalls(){
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        for (Hall hall : hallList){
            halls.add(hall);
        }
        return halls;
    }

}
