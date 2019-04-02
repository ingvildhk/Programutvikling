package org.kulturhusfx.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arrangement {

    ContactPerson kontaktperson;
    String navn;
    String opptredendePersoner;
    String program;
    Hall sted;
    String tidspunkt;
    double billettpris;

    public Arrangement(ContactPerson kontaktperson, String navn, String opptredendePersoner,
                       String program, Hall sted, String tidspunkt, double billettpris) {
        this.kontaktperson = kontaktperson;
        this.navn = navn;
        this.opptredendePersoner = opptredendePersoner;
        this.program = program;
        this.sted = sted;
        this.tidspunkt = tidspunkt;
        this.billettpris = billettpris;
    }

    public int solgteBilletter() {
        return 0;
    }

    public void visAlleArrangementer() {
    }

    public void leggTilArrangement(Arrangement o) {

    }

    public void slettArrangement(Arrangement o) {
    }

    public void endreArrangement(Arrangement o) {
    }

    // Metode for å sjekke om Dato-input er i riktig format
    private boolean checkValidDate(String date) throws InvalidDateException {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (validDate == false) throw new InvalidDateException
                ("Husk å skrive inn dato i riktig format, feks: 12/12/2013. Arrangement ble ikke oppdatert ");
        return true;
    }




}
