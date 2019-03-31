package org.kulturhusfx.base;


public class Arrangement {

    KontaktPerson kontaktperson;
    String navn;
    String opptredendePersoner;
    String program;
    Lokale sted;
    String tidspunkt;
    double billettpris;

    public Arrangement(KontaktPerson kontaktperson, String navn, String opptredendePersoner,
                       String program, Lokale sted, String tidspunkt, double billettpris) {
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


}
