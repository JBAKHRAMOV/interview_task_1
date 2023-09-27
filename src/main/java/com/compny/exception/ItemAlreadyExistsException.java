package com.compny.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String message) {
        super("ERROR MASSAGE: " + message);
    }
}
