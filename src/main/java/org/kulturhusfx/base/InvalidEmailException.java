package org.kulturhusfx.base;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String msg) {

        super(msg);
    }
}
