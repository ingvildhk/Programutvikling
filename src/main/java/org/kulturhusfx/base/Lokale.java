package org.kulturhusfx.base;

public class Lokale {
    private String salNavn;
    private String typeSal;
    private int antallPlasser;

    public Lokale(String salNavn, String typeSal, int antallPlasser) {
        this.salNavn = salNavn;
        this.typeSal = typeSal;
        this.antallPlasser = antallPlasser;
    }

    public String getSalNavn() {
        return salNavn;
    }

    public String getTypeSal() {
        return typeSal;
    }

    public int getAntallPlasser() {
        return antallPlasser;
    }

    public void setSalNavn(String navn) {
        this.salNavn = navn;
    }

    public void setTypeSal(String typeSal) {
        this.typeSal = typeSal;
    }

    public void setAntallPlasser(int antallPlasser) {
        this.antallPlasser = antallPlasser;
    }
}
