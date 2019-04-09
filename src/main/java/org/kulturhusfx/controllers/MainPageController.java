package org.kulturhusfx.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML
    private TableView<Event> tableViewEvents;
    @FXML
    private TableColumn<Event, String> EventColumn, TypeColumn, DateColumn, AvailableColumn;
    @FXML
    private TableColumn<Event, Void> OrderColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    private ObservableList<Event> getEvents(){
        //eventOrder er en ny klasse som kun tar inn den informasjonen vi vil vise på forsiden
        ObservableList<Event> events = FXCollections.observableArrayList();
        Hall hovedsal = hallList.get(0);
        if (eventList == null || eventList.isEmpty()){
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 1",
                    "folk", "konsert","program", hovedsal, "10/11/11", "10:10","200.00");
            eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 2",
                    "folk", "foredrag","program", hovedsal, "10/11/11", "10:10","200.00");
        }
        for (Event event : eventList){
            events.add(event);
        }
        return events;
    }

    public void addButton(){
        //legger til knapper i bestill-kolonnen
        OrderColumn.setCellFactory(col -> {
            Button orderButton = new Button("Bestill");
            TableCell<Event, Void> cell = new TableCell<Event, Void>() {
                @Override
                public void updateItem(Void v, boolean empty) {
                    super.updateItem(v, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(orderButton);
                    }
                }
            };

            orderButtonAction(orderButton);
            return cell ;
        });
    }

    public void orderButtonAction(Button button){
        //Cannot use SceneUtils.launch as it gives error
        //Cannot use node with lambda expressions apparently
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("purchaseTicket.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Programutvikling Semesteroppgave");
        stage.setScene(scene);
        button.setOnAction(e -> stage.show());
    }

    public void initialize() {
        // TODO
        if (hallModel.getHallList().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }
        EventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("numberOfTickets"));
        tableViewEvents.setItems(getEvents());
        addButton();
    }
}
