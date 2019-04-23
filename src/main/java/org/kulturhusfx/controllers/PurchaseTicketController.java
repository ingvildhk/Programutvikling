package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.TicketModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.util.List;

public class PurchaseTicketController {

    //Hvis vi vil skrive ut hvilket billettnummer, altså hvilken plass man får når man bestiller
    //så blir billettnummeret ticketList.getIndex(ticket) + 1

    //Igjen, antakelig ikke slik vi bør gjøre det, men currentNumberofTickets er statisk slik at vi kan
    //få den med oss til TicketConfirmation
    private Event currentEvent = MainPageController.currentEvent;
    public static String currentNumberofTickets;

    public TicketModel ticketModel = currentEvent.getTicketModel();
    public List<Ticket> ticketList = ticketModel.getTicketList();

    @FXML
    private Label eventLabel, hallLabel, performersLabel, dateLabel, timeLabel, typeLabel, scheduleLabel, ticketPriceLabel;
    @FXML
    private TextField phoneTxtField;
    @FXML
    private ChoiceBox numberOfTicketsChoiceBox;

    private EventModel eventModel = EventModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();

    public void initialize(){
        setLabels();
        numberOfTicketsChoiceBox.getItems().addAll("1", "2", "3", "4", "5","6", "7", "8", "9");
        numberOfTicketsChoiceBox.setValue("1");
    }

    public void orderBtn(ActionEvent event){
        createNewTickets();
        SceneUtils.launchScene(event, AdminMainPageController.class, "ticketConfirmationPop.fxml");
    }

    public void backToMainPageBtn(ActionEvent event) {
        SceneUtils.launchScene(event, MainPageController.class,"MainPage.fxml" );
    }

    private void setLabels(){
        eventLabel.setText(currentEvent.getName());
        hallLabel.setText(currentEvent.getHall().getHallName());
        performersLabel.setText(currentEvent.getPerformers());
        dateLabel.setText(currentEvent.getDate());
        timeLabel.setText(currentEvent.getTime());
        typeLabel.setText(currentEvent.getType());
        scheduleLabel.setText(currentEvent.getSchedule());
        ticketPriceLabel.setText(currentEvent.getTicketPrice());
    }

    private void createNewTickets(){
        String phone = phoneTxtField.getText();
        Checker.checkIfFieldIsEmpty(phone);
        Checker.checkIfChoiceBoxIsEmpty(numberOfTicketsChoiceBox);
        String numberOfTickets = numberOfTicketsChoiceBox.getValue().toString();

        int orderManyTickets = Integer.parseInt(numberOfTickets);
        currentNumberofTickets = numberOfTickets;

        if (ticketList.size() >=  Integer.parseInt(currentEvent.getHall().getNumberOfSeats())){
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Arrangementet er utsolgt"));
        }

        if (ticketList.size() + orderManyTickets >  Integer.parseInt(currentEvent.getHall().getNumberOfSeats())){
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Ikke nok ledige plasser til å kjøpe så mange billetter"));
        }

        //Oppretter nye billettobjekter basert på hvor mange billetter man vil bestille
        for (int i = 1; i <= orderManyTickets; i++){
            ticketModel.createTicket(phone);
        }

        //setter hvor mange billetter som er ledige
        currentEvent.setAvailableTickets(currentEvent.getAvailableTickets() - orderManyTickets);
    }
}
