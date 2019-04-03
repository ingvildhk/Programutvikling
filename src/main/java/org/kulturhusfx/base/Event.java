package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;

public class Event {

    private static int counter = 0;

    private ContactPerson contactPerson;
    private String id;
    private String name;
    private String performers;
    private String schedule;
    private Hall location;
    private String date;
    private String time;
    private String ticketPrice;

    //Kjøres etter inlest fil for å kontrollere at id-ene holdes unike
    public static void setMinId(int id) {
        if (id > counter) {
            counter = id;
        }
    }

    public Event(ContactPerson contactPerson, String name, String performers,
                 String schedule, Hall location, String date, String time, String ticketPrice) {
        Checker.checkValidDate(date);
        //Checker.checkValidTime(time) - metode må opprettes
        //Checker.checkValidTicketPrice(ticketPrice) - metode må opprettes
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

    public ContactPerson getContactPerson(){
        return contactPerson;
    }

    public String getName(){
        return name;
    }

    public String getPerformers(){
        return performers;
    }

    public String getSchedule(){
        return  schedule;
    }

    public Hall getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getId(){
        return id;
    }

    private void setContactPerson(ContactPerson contactPerson){
        this.contactPerson = contactPerson;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setPerformers(String performers){
        this.performers = performers;
    }

    private void setSchedule(String schedule){
        this.schedule = schedule;
    }

    private void setLocation(Hall location){
        this.location = location;
    }

    private void setDate(String date){
        this.date = date;
    }

    private void setTime(String time){
        this.time = time;
    }

    private void setTicketPrice(String ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    public void changeEventInformation(ContactPerson contactPerson, String name,
                                       String performers, String schedule, Hall location,
                                       String date, String time, String ticketPrice){
        Checker.checkValidDate(date);
        //Checker.checkValidTime(date) - metode må opprettes
        //Checker.checkValidTicketPrice(ticketPrice) - metode må opprettes
        setContactPerson(contactPerson);
        setName(name);
        setPerformers(performers);
        setSchedule(schedule);
        setLocation(location);
        setDate(date);
        setTime(time);
        setTicketPrice(ticketPrice);
    }

    public int soldTickets() {
        return 0;
    }

    /* ganske sikker på at disse bør være i EventModel-klassen da den holder styr på liste over alle events
    public void showAllEvents() {
    }

    public void addEvenet(Event o) {
    }

    public void removeEvent(Event o) {
    }

    public void editEvent(Event o) {
    }*/

}
