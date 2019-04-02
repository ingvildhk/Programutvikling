package org.kulturhusfx.base;

import org.kulturhusfx.controllers.uihelpers.InvalidInputHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {

    ContactPerson contactPerson;
    String name;
    String performers;
    String schedule;
    Hall location;
    String date;
    String time;
    double ticketPrice;

    public Event(ContactPerson contactPerson, String name, String performers,
                 String schedule, Hall location, String date, String time, double ticketPrice) {
        this.contactPerson = contactPerson;
        this.name = name;
        this.performers = performers;
        this.schedule = schedule;
        this.location = location;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
    }

    public int soldTickets() {
        return 0;
    }

    public void showAllEvents() {
    }

    public void addEvenet(Event o) {

    }

    public void removeEvent(Event o) {
    }

    public void editEvent(Event o) {
    }

    // Metode for å sjekke om Dato-input er i riktig format
    private boolean checkValidDate(String date) throws InvalidDateException {
        // Sjekk at regexen er riktig, fjern kommentar når gjort
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (validDate == false) {
            InvalidInputHandler.generateAlert("Datoformat feil. Skriv inn dato i riktig format, feks. 02/05/2019. " +
                    "Event ble ikke oppdatert");
            throw new InvalidDateException
                    ("Datoformat feil. Event ble ikke oppdatert ");
        }
        return true;
    }




}
