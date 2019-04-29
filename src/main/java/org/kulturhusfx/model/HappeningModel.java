package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HappeningModel implements Serializable {

    private static final HappeningModel instance = new HappeningModel();

    private HappeningModel() {
    }

    public static HappeningModel getInstance() {
        return instance;
    }

    private List<Happening> happeningList = new ArrayList<>();

    public void createHappening(ContactPerson contactPerson, String name, String performers, String schedule,
                                Hall hall, String type, String date, String time, String ticketPrice) {
        happeningList.add(new Happening(contactPerson, name, performers, schedule, hall, type, date, time, ticketPrice));
    }

    public List<Happening> getHappeningList() {
        return happeningList;
    }
}
