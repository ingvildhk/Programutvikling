package org.kulturhusfx.base;

import java.util.Date;

public class Ticket {

    private int telefonnrKjoper;
    private int plassnr;
    private Date kjopstidspunkt;

    public Ticket(int telefonnrKjoper) {
        this.telefonnrKjoper = telefonnrKjoper;
    }

    public void setTelefonnrKjoper(int telefonnrKjoper) {
        this.telefonnrKjoper = telefonnrKjoper;
    }

    public int getTelefonnrKjoper() {
        return telefonnrKjoper;
    }

    public int genererPlass() {

        return 0;
    }
}

