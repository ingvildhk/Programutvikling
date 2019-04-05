package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;

import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private List<Event> eventList;

    public EventModel(){
        if(eventList == null){
            eventList = new ArrayList<>();
        }
    }

    public void createEvent(ContactPerson contactPerson, String name, String performers, String type, String schedule,
                            Hall location, String date, String time, String ticketPrice){
        eventList.add(new Event(contactPerson, name, performers, schedule, location, type, date, time, ticketPrice));
    }

    public List getEventList(){
        return eventList;
    }

    public void deleteEvent(String id) {
        eventList.removeIf(e -> e.getId().equals(id));
    }

    public void deleteHallEvents(String hallName){
        eventList.removeIf(e -> e.getLocation().getHallName().equals(hallName));
    }
}
