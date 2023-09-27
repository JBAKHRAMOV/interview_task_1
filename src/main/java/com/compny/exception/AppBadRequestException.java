package com.compny.exception;

public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(String message) {
        super("ERROR MESSAGE: " + message);
    }
}
