package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.ControllerHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Ticket {

    private String phoneNumber;

    public Ticket(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

