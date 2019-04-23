package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import java.io.Serializable;

public class ContactPerson implements Serializable {
    private String contactName;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String firm;
    private String otherInformation;

    //nettside, firma og andreOpplysninger er forsatt med i konstruktøren selv om de er valgfrie, da de kan
    //være tomme uten at det skaper noen problemer
    public ContactPerson(String name, String phoneNumber, String email, String webpage,
                         String firm, String otherInformation) {
        Checker.checkValidPhone(phoneNumber);
        Checker.checkValidEmail(email);
        this.contactName = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.webpage = webpage;
        this.firm = firm;
        this.otherInformation = otherInformation;
    }

    public String getContactName() {
        return contactName;
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

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    // TODO Er denne nødvendig? Vurdere etterhvert om den blir brukt
    public void changeContactPersonInformation(String name, String phoneNumber, String email,
                                               String webpage, String firm, String otherInformation) {
        Checker.checkValidEmail(email);
        Checker.checkValidPhone(phoneNumber);
        setContactName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setWebpage(webpage);
        setFirm(firm);
        setOtherInformation(otherInformation);
    }

    // TODO trenger ikke toString metoder i levering
    public String toString() {
        String s = contactName + " " + phoneNumber + " " + email + " " + webpage + " " + firm + " " + otherInformation;
        return s;
    }

}
