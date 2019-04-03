package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidContactPersonException;

import java.util.HashMap;
import java.util.Map;

public class ContactPersonModel {
    private Map<String, ContactPerson> contactPersonMap;

    public ContactPersonModel(){
        contactPersonMap = new HashMap<>();
    }

    public void createContactPerson(String name, String phoneNumber,
                                    String email, String webpage, String firm, String otherInformation){
        if (contactPersonMap.containsKey(email)){
            InvalidInputHandler.generateAlert(new InvalidContactPersonException("Det er allerede registrert " +
                    "en person på denne eposten"));
        }
        contactPersonMap.put(email, new ContactPerson(name, phoneNumber, email,
                webpage, firm, otherInformation));
    }

    public void deleteContactPerson(String email){
        contactPersonMap.entrySet().removeIf(e -> e.getKey().equals(email));
    }
}
