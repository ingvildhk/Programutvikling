package org.kulturhusfx.model;


import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidHallException;

import java.util.HashMap;
import java.util.Map;

public class HallModel {
    //public for øyeblikket slik at man skal kunne skrive ut med system.out, kun for kontroll
    public static Map<String, Hall> hallMap;

    public HallModel() {
        if(hallMap == null){
            hallMap = new HashMap<>();
        }
    }

    public Map getHallMap(){
        return hallMap;
    }

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        if (hallMap.containsKey(hallName)) {
            InvalidInputHandler.generateAlert(new InvalidHallException("Salen finnes fra før av"));
        }
        hallMap.put(hallName, new Hall(hallName, hallType, numberOfSeats));
    }

    public void deleteHall(String hallName){
        hallMap.entrySet().removeIf(e -> e.getKey().equals(hallName));
    }
}
