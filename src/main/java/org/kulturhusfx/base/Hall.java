package org.kulturhusfx.base;

import org.kulturhusfx.controllers.uihelpers.InvalidInputHandler;

public class Hall {
    private String hallName;
    private String hallType;
    private String numberOfSeats;

    public Hall(String hallName, String hallType, String numberOfSeats) {
        this.hallName = hallName;
        this.hallType = hallType;
        this.checkValidNumberOfSeats(numberOfSeats);
        this.numberOfSeats = numberOfSeats;
    }

    public String getHallName() {
        return hallName;
    }

    public String getHallType() {

        return hallType;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setHallName(String hallName) {

        this.hallName = hallName;
    }

    public void setHallType(String hallType) {

        this.hallType = hallType;
    }

    public void setNumberOfSeats(String numberOfSeats) {

        this.numberOfSeats = numberOfSeats;
    }

    public void checkValidNumberOfSeats(String numberOfSeats) {
        try {
            int seat = Integer.parseInt(numberOfSeats);
            if(seat < 0) {
                InvalidInputHandler.generateAlert(
                        new InvalidNumberOfSeatsException("Antall seter kan ikke være et negativt tall"));
            }
        }
        catch(NumberFormatException e){
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Antall seter må være et tall"));
        }
    }
}
