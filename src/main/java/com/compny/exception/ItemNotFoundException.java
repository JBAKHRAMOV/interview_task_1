package com.compny.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super("ERROR MASSAGE: " + message);
    }
}
