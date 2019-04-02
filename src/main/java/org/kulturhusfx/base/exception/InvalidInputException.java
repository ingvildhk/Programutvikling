package org.kulturhusfx.base.exception;

//This is for handling empty Strings - aka no input in required fields

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String msg) {

        super(msg);
    }
}