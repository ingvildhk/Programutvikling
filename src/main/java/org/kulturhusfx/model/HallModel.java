package org.kulturhusfx.model;


import org.kulturhusfx.base.Hall;
import java.util.HashMap;
import java.util.Map;

public class HallModel {
    public Map<String, Hall> halls;

    public HallModel() {
        this.halls = new HashMap<>();
    }

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        if (halls.containsKey(hallName)) {
            // Kast en exception her
        }
        halls.put(hallName, new Hall(hallName, hallType, numberOfSeats));
    }

    public void deleteHall(String hallName){

        halls.entrySet().removeIf(e -> e.getKey().equals(hallName));
    }
}
