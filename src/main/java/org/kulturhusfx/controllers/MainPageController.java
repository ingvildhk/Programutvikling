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
    private TableView<eventOrder> tableViewEvents;
    @FXML
    private TableColumn<eventOrder, String> EventColumn, TypeColumn, DateColumn, AvailableColumn;
    @FXML
    private TableColumn<eventOrder, Void> OrderColumn;

    private HallModel hallModel = HallModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();
    private List<Hall> hallList = hallModel.getHallList();


    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }


    private ObservableList<eventOrder> getEvents(){
        //eventOrder er en ny klasse som kun tar inn den informasjonen vi vil vise på forsiden
        ObservableList<eventOrder> events = FXCollections.observableArrayList();
        Hall hovedsal = hallList.get(0);

        eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 1",
                "folk", "konsert","program", hovedsal, "10/11/11", "10:10","200.00");
        eventModel.createEvent((new ContactPerson("Kontaktperson 1", "12345678", "hei@mail.no","","", "")), "Dummy Arrangement 2",
                "folk", "foredrag","program", hovedsal, "10/11/11", "10:10","200.00");

        for (Event event : eventList){
            eventOrder eventOrder = new eventOrder(event.getName(), event.getType(), event.getDate());
            events.add(eventOrder);
        }
        return events;
    }

    public void initialize() {
        // TODO
        if (hallModel.getHallList().isEmpty()) {
            hallModel.createHall("Hovedsalen", "Konsertsal", "150");
        }
        EventColumn.setCellValueFactory(new PropertyValueFactory<eventOrder, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<eventOrder, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<eventOrder, String>("date"));
        tableViewEvents.setItems(getEvents());
    }

    //Lagde en ny klasse, som bare inneholder den informasjonen vi vil vise i billettbestilling
    //Dette er fordi da jeg prøvde å gjøre det med den vanlige Eventklassen fikk jeg feilmeldinger da
    //kolonnene ikke klarte å hente ut den dataen vi ville vise, og ga en feilmelding
    //Hvis vi vil vise andre ting på forsiden må vi dermed legge til det i denne klassen som f.eks
    //private int availabletickets - når vi kommer så langt
    public class eventOrder{
        private String name;
        private String type;
        private String date;

        public eventOrder(String name, String type, String date){
            this.name = name;
            this.type = type;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getDate() {
            return date;
        }
    }
}
