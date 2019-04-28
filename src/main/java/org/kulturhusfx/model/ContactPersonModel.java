package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidContactPersonException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContactPersonModel implements Serializable {

    //TODO Hele denne klassen brukes ikke, da kontaktpersoner ikke blir lagret i en egen liste
    //TODO Men kun i Hall og Hallmodel. ContactPersonModel kan nok fjernes
    /*

    private static final ContactPersonModel contactPersonModel = new ContactPersonModel();


    private ContactPersonModel() {
    }

    public static ContactPersonModel getInstance() {
        return contactPersonModel;
    }
*/


    private Map<String, ContactPerson> contactPersonMap;

    public ContactPersonModel() {
        if (contactPersonMap == null) {
            contactPersonMap = new HashMap<>();
        }
    }

    public void createContactPerson(String name, String phoneNumber,
                                    String email, String webpage, String firm, String otherInformation) {
        if (contactPersonMap.containsKey(email)) {
            InvalidInputHandler.generateAlert(new InvalidContactPersonException("Det er allerede registrert " +
                    "en person på denne eposten"));
        }
        contactPersonMap.put(email, new ContactPerson(name, phoneNumber, email,
                webpage, firm, otherInformation));
    }

    public void deleteContactPerson(String email) {
        contactPersonMap.entrySet().removeIf(e -> e.getKey().equals(email));
    }

}