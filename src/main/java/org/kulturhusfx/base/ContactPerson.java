package org.kulturhusfx.base;

import java.io.Serializable;

public class ContactPerson implements Serializable {
    private String contactName;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String firm;
    private String otherInformation;

    public ContactPerson(String name, String phoneNumber, String email, String webpage,
                         String firm, String otherInformation) {
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

    // TODO Private?
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
}
