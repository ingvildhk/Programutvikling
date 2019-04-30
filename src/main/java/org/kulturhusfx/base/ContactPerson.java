package org.kulturhusfx.base;

import java.io.Serializable;

public class ContactPerson implements Serializable {
    private String contactName;
    private String phoneNumber;
    private String email;
    private String webpage;
    private String firm;
    private String otherInformation;

    //SerialVersionUID makes sure that the object is correctly serialized when reading from .jobj file
    private static final long serialVersionUID = 6604539640364471825L;

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

    //package-private as they are accessed only through the happening class
    void setContactName(String contactName) {
        this.contactName = contactName;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    void setFirm(String firm) {
        this.firm = firm;
    }

    void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }
}
