package com.compny.exception;

public class UserBlockException extends RuntimeException {
    public UserBlockException(String message) {
        super("ERROR MESSAGE: " + message);
    }
}
