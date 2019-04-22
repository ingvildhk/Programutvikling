package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket implements Serializable {

    //TODO Date må implementeres
    private String phoneNumber;
    private Date timeOfPurchase;

    public Ticket(String phoneNumber) {
        Checker.checkValidPhone(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDate(){
        return timeOfPurchase;
    }
}

