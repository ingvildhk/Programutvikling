package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.model.TicketModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.io.IOException;
import java.util.List;

import static org.kulturhusfx.util.Checker.exceptionAlertWrapper;

public class PurchaseTicketController {

    //TODO copy managehappeningscontroller?
    private Happening currentHappening = MainPageController.currentHappening;
    public static String currentNumberofTickets;

    private TicketModel ticketModel = currentHappening.getTicketModel();
    private List<Ticket> ticketList = ticketModel.getTicketList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    @FXML
    private Label happeningLabel, hallLabel, performersLabel, dateLabel, timeLabel, typeLabel, scheduleLabel, ticketPriceLabel;
    @FXML
    private TextField phoneTxtField;
    @FXML
    private ChoiceBox <String> numberOfTicketsChoiceBox;

    public void initialize() {
        setLabels();
        setDefaultValueToNumberOfTicketsChoiceBox();
        addAlternativesToNumberOfTicketsChoiceBox();

    }

    private void setLabels() {
        happeningLabel.setText(currentHappening.getName());
        hallLabel.setText(currentHappening.getHall().getHallName());
        performersLabel.setText(currentHappening.getPerformers());
        dateLabel.setText(currentHappening.getDate());
        timeLabel.setText(currentHappening.getTime());
        typeLabel.setText(currentHappening.getType());
        scheduleLabel.setText(currentHappening.getSchedule());
        ticketPriceLabel.setText(currentHappening.getTicketPrice());
    }

    private void setDefaultValueToNumberOfTicketsChoiceBox(){
        numberOfTicketsChoiceBox.setValue("1");
    }

    private void addAlternativesToNumberOfTicketsChoiceBox(){
        numberOfTicketsChoiceBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    public void orderBtn(ActionEvent event) throws IOException {
        createNewTickets();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticketConfirmationPop.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void createNewTickets() {
        String phone = phoneTxtField.getText();
        exceptionAlertWrapper(() -> Checker.checkIfFieldIsEmpty(phone));
        exceptionAlertWrapper(() -> Checker.checkValidPhone(phone));
        exceptionAlertWrapper(() -> Checker.checkIfChoiceBoxIsEmpty(numberOfTicketsChoiceBox));
        String numberOfTickets = numberOfTicketsChoiceBox.getValue().toString();

        int orderManyTickets = Integer.parseInt(numberOfTickets);
        currentNumberofTickets = numberOfTickets;

        if (ticketList.size() >= Integer.parseInt(currentHappening.getHall().getNumberOfSeats())) {
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Arrangementet er utsolgt"));
        }

        if (ticketList.size() + orderManyTickets > Integer.parseInt(currentHappening.getHall().getNumberOfSeats())) {
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Ikke nok ledige plasser til å kjøpe så mange billetter"));
        }

        //creates new tickets equal to the number ordered
        for (int i = 1; i <= orderManyTickets; i++) {
            ticketModel.createTicket(phone);
        }

        //updates available tickets
        currentHappening.setAvailableTickets(currentHappening.getAvailableTickets() - orderManyTickets);
    }

    public void backToMainPageBtn(ActionEvent event) {
        sceneUtils.launchScene(event, MainPageController.class, "MainPage.fxml");
    }
}
