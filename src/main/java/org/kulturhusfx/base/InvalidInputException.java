package org.kulturhusfx.base;

//This is for handling empty Strings - aka no input in required fields

public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {

        super(msg);
    }
}