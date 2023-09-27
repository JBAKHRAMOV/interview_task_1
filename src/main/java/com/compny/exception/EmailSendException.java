package com.compny.exception;

public class EmailSendException extends RuntimeException {
    public EmailSendException(String message) {
        super("ERROR MESSAGE: " + message);
    }
}
