package org.kulturhusfx.base;

import org.kulturhusfx.base.exception.InvalidDateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {

    private static int counter = 0;

    ContactPerson contactPerson;
    String id;
    String name;
    String performers;
    String schedule;
    Hall location;
    String date;
    String time;
    double ticketPrice;

    // Settes til max etter lest inn fil
    public static void setMinId(int id) {
        if (id > counter) {
            counter = id;
        }
    }

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
        this.id = "" + counter++;
    }

    public String getId(){
        return id;
    }

    public Hall getLocation(){
        return location;
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
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (validDate == false) throw new InvalidDateException
                ("Husk å skrive inn dato i riktig format, feks: 12/12/2013. Event ble ikke oppdatert ");
        return true;
    }




}
