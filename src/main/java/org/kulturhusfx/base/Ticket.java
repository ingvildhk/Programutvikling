package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.util.Date;

public class Ticket {

    private static int counter = 0;

    private int seatNumber;
    private String phoneNumber;
    private Date timeOfPurchase;
    private Event event;

    public Ticket(String phoneNumber, Event event) {
        //sjekker om telefonnummer er et gyldig nummer før objektet opprettes
        Checker.checkValidPhone(phoneNumber);
        //sjekker om det er ledige billetter igjen
        if (Integer.parseInt(event.getLocation().getNumberOfSeats()) >= counter) {
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Arrangementet er utsolgt"));
        }
        this.event = event;
        this.phoneNumber = phoneNumber;
        this.seatNumber = counter++;
        addTicketToList();
    }

    private void addTicketToList() {
        event.getTicketList().createTicket(phoneNumber, event);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Event getEvent() {
        return event;
    }

    /*public Date getDate(){
        return date;
    }*/

    public int getSeatNumber() {
        return seatNumber;
    }

}

