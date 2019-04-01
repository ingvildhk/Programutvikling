package org.kulturhusfx.base;

public class KontaktPerson {
    private String navn;
    private String telefonnummer;
    private String epost;
    private String nettside;
    private String firma;
    private String andreOpplysninger;

    //nettside, firma og andreOpplysninger er ikke i konstruktøren da de er valgfrie opplysninger
    public KontaktPerson(String navn, String telefonnummer, String epost) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
    }

    public String getNavn() {
        return navn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public String getNettside() {
        return nettside;
    }

    public String getFirma() {
        return firma;
    }

    public String getAndreOpplysninger() {
        return andreOpplysninger;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public void setNettside(String nettside) {
        this.nettside = nettside;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public void setAndreOpplysninger(String andreOpplysninger) {
        this.andreOpplysninger = andreOpplysninger;
    }


}
