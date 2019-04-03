package org.kulturhusfx.model;


import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.InvalidInputHandler;

import java.util.HashMap;
import java.util.Map;

public class HallModel {
    private Map<String, Hall> hallMap;

    public HallModel() {
        this.hallMap = new HashMap<>();
    }

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        if (hallMap.containsKey(hallName)) {
            //InvalidInputHandler.generateAlert(new InvalidHallNameException("Salen finnes fra før av");
        }
        hallMap.put(hallName, new Hall(hallName, hallType, numberOfSeats));
    }

    public void deleteHall(String hallName){

        hallMap.entrySet().removeIf(e -> e.getKey().equals(hallName));
    }
}
