package org.kulturhusfx.base;

import org.kulturhusfx.controllers.uihelpers.InvalidInputHandler;

public class ContactPerson {
    private String name;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String firm;
    private String otherInformation;

    //nettside, firma og andreOpplysninger er ikke i konstruktøren da de er valgfrie opplysninger
    public ContactPerson(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebpage() {
        return webpage;
    }

    public String getFirm() {
        return firm;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public boolean checkValidEmail(String email) throws InvalidEmailException {
        String[] splitEmail = email.split("@");
        if (splitEmail.length != 2) {
            InvalidInputHandler.generateAlert(new InvalidEmailException("Epost må inneholde '@'"));
        }
        return true;
    }

}
