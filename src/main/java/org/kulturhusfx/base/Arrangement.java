package org.kulturhusfx.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Arrangement {

    KontaktPerson kontaktperson;
    String navn;
    String opptredendePersoner;
    String program;
    Lokale sted;
    String date;
    String tidspunkt;
    double billettpris;

    public Arrangement(KontaktPerson kontaktperson, String navn, String opptredendePersoner,
                       String program, Lokale sted, String date, String tidspunkt, double billettpris) {
        this.kontaktperson = kontaktperson;
        this.navn = navn;
        this.opptredendePersoner = opptredendePersoner;
        this.program = program;
        this.sted = sted;
        this.date = date;
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

    // Metode for å sjekke om Dato-input er riktig
    private boolean checkValidDate(String date) throws InvalidDateException {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean validDate = matcher.matches();
        if (validDate == false) throw new InvalidDateException
             ("Husk å skrive inn gyldig dato. Bruk dette formatet feks:  12/12/2013");
        return true;
    }

}
