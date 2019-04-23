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
import org.kulturhusfx.util.OrderButton;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    //Dette er ok :)
    public static Event currentEvent;

   @FXML
    private TableView<Event> tableViewEvents;
    @FXML
    private TableColumn<Event, String> EventColumn, TypeColumn, DateColumn, TimeColumn, AvailableColumn;
    @FXML
    private TableColumn<Event, Boolean> OrderColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();


    public void initialize(){
        //Skal bort etterhvert, når vi finner ut hvordan man leser fra fil
        if (hallModel.getHallList().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }

        addButtons();
        setColumnValues();
    }

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    private void addButtons(){
        OrderColumn.setCellValueFactory(
                property -> new SimpleBooleanProperty(property.getValue() != null));

        //Trenger ikke denne koden da den er erstattet med lambda. Ligger her i tilfelle noe går galt
                /*new Callback<TableColumn.CellDataFeatures<Event, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Event, Boolean> property) {
                        return new SimpleBooleanProperty(property.getValue() != null);
                    }
                });*/

        //Legger til knapp i cell
        OrderColumn.setCellFactory(
                property -> new OrderButton());

        /*Trenger ikke denne koden da den er erstattet med lambda. Ligger her i tilfelle noe går galt
                new Callback<TableColumn<Event, Boolean>, TableCell<Event, Boolean>>() {

                    @Override
                    public TableCell<Event, Boolean> call(TableColumn<Event, Boolean> property) {
                        return new OrderButton();
                    }
                });*/
    }

    private void setColumnValues(){
        EventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("availableTickets"));
        tableViewEvents.setItems(getEvents());
    }

    private ObservableList<Event> getEvents(){
        ObservableList<Event> events = FXCollections.observableArrayList();

        //Skal bort etterhvert, når vi finner ut hvordan man leser fra fil
        Hall hovedsal = hallList.get(0);
        if (eventList == null || eventList.isEmpty()){
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 1",
                    "folk","program", hovedsal, "konsert", "2019-09-21", "10:10","200.00");
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 2",
                    "folk","program", hovedsal, "foredrag", "2020-09-22", "10:10","200.00");
        }

        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }
}
