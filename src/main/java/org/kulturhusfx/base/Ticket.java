package org.kulturhusfx.base;

import org.kulturhusfx.controllers.uihelpers.InvalidInputHandler;

import java.util.Date;

public class Ticket {

    private static int seat = 0;
    private String phoneNumber;
    private int seatNumber;
    private Date timeOfPurchase;

    public Ticket(String phoneNumber) {

        this.phoneNumber = phoneNumber;
        seat++;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public int generateSeat() {

        return 0;
    }

    public boolean checkValidPhone(String phoneNumber) throws InvalidPhoneException {
        try {
            int phone = Integer.parseInt(phoneNumber);
            if(phone < 0) {
                InvalidInputHandler.generateAlert("Telefonnummer kan ikke være et negativt tall");
                throw new InvalidPhoneException("Telefonnummer kan ikke være et negativt tall");
            }
        }
        catch(NumberFormatException e){
            InvalidInputHandler.generateAlert("Telefonnummer må være et tall");
            throw new InvalidPhoneException("Telefonnummer må være et tall");
        }

        if (phoneNumber.length()!= 8) {
            InvalidInputHandler.generateAlert("Telefonnummer må være 8 siffer");
            throw new InvalidPhoneException("Telefonnummer må være 8 siffer");
        }
        return true;
    }
}

