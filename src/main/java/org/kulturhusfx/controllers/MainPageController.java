package org.kulturhusfx.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    //Ikke slik vi bør gjøre det,er jeg ganske sikker på, men currentEvent er statisk slik at vi skal
    //få tak i det i purchaseTicket
    public static Event currentEvent;

   @FXML
    private TableView<Event> tableViewEvents;
    @FXML
    private TableColumn<Event, String> EventColumn, TypeColumn, DateColumn, AvailableColumn;
    @FXML
    private TableColumn<Event, Boolean> OrderColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();


    public void initialize(){
        if (hallModel.getHallList().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }

        EventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("availableTickets"));
        tableViewEvents.setItems(getEvents());
        addButtons();
    }

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    private ObservableList<Event> getEvents(){
        ObservableList<Event> events = FXCollections.observableArrayList();
        Hall hovedsal = hallList.get(0);
        if (eventList == null || eventList.isEmpty()){
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 1",
                    "folk", "konsert","program", hovedsal, "2019-09-21", "10:10","200.00");
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 2",
                    "folk", "foredrag","program", hovedsal, "2020-09-22", "10:10","200.00");
        }
        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }

    private void addButtons(){
        OrderColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Event, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Event, Boolean> property) {
                        return new SimpleBooleanProperty(property.getValue() != null);
                    }
                });

        //Legger til knapp i cell
        OrderColumn.setCellFactory(
                new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {

                    @Override
                    public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> property) {
                        return new OrderButtonAction();
                    }
                });
    }
    }

     class OrderButtonAction extends TableCell<Event, Boolean> {
        final Button cellButton = new Button("Bestill");

        OrderButtonAction(){
            //Hva som skjer når man trykker på bestill
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    ///Henter ut det eventet som er på raden man trykker på
                    MainPageController.currentEvent = OrderButtonAction.this.getTableView().getItems().get(OrderButtonAction.this.getIndex());
                    SceneUtils.launchScene(event, MainPageController.class, "purchaseTicket.fxml");
                }
            });
        }

        //Legger til knapp hvis raden ikke er tom
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
}
