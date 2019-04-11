package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.util.Date;

public class Ticket {

    private static int counter = 0;

    private String phoneNumber;
    private Date timeOfPurchase;

    public Ticket(String phoneNumber) {
        Checker.checkValidPhone(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*public Date getDate(){
        return date;
    }*/
}

