package com.compny.exception.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ErrorDto implements Serializable {
    private final String errorPath;
    private final String errorMessage;
    private final Integer errorCode;
    private final Long timestamp;

    public ErrorDto(String errorPath, String errorMessage, Integer errorCode) {
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }
}
