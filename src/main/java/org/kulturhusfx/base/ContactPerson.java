package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;

public class ContactPerson {
    private String name;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String firm;
    private String otherInformation;

    //nettside, firma og andreOpplysninger er forsatt med i konstruktøren selv om de er valgfrie, da de kan
    //være tomme uten at det skaper noen problemer
    public ContactPerson(String name, String phoneNumber, String email, String webpage,
                         String firm, String otherInformation) {
        Checker.checkValidEmail(email);
        Checker.checkValidPhone(phoneNumber);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.webpage = webpage;
        this.firm = firm;
        this.otherInformation = otherInformation;
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

    private void setName(String name) {
        this.name = name;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    private void setFirm(String firm) {
        this.firm = firm;
    }

    private void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public void changeContactPersonInformation(String name, String phoneNumber, String email,
                                               String webpage, String firm, String otherInformation) {
        Checker.checkValidEmail(email);
        Checker.checkValidPhone(phoneNumber);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setWebpage(webpage);
        setFirm(firm);
        setOtherInformation(otherInformation);
    }

    public String toString() {
        String s = name + " " + phoneNumber + " " + email + " " + webpage + " " + firm + " " + otherInformation;
        return s;
    }

}
