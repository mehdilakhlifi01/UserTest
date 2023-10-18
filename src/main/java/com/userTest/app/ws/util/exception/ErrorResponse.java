package com.userTest.app.ws.util.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter @Setter
public class ErrorResponse {

    private String message;

    private String description;

    private Map<String, String> metaData;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public ErrorResponse(String message, Map<String, String> metaData) {
        this.message = message;
        this.metaData = metaData;
    }



}
