package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventModel implements Serializable {

    private static final EventModel instance = new EventModel();

    private EventModel(){
    }

    public static EventModel getInstance(){
        return instance;
    }

    private List<Event> eventList = new ArrayList<Event>();

    public void createEvent(ContactPerson contactPerson, String name, String performers, String schedule,
                            Hall location, String type, String date, String time, String ticketPrice) {
        eventList.add(new Event(contactPerson, name, performers, schedule, location, type, date, time, ticketPrice));
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void deleteEvent(String id) {
        eventList.removeIf(e -> e.getId().equals(id));
    }

    public void deleteHallEvents(String hallName) {
        eventList.removeIf(e -> e.getHall().getHallName().equals(hallName));
    }
}




    /*public EventModel() {
        if (eventList == null || eventList.isEmpty()) {
            eventList = new ArrayList<>();
        }
    }*/