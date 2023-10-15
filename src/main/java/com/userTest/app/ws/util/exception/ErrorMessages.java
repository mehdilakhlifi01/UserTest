package com.userTest.app.ws.util.exception;

public enum ErrorMessages {
    MISSING_REQUUIRED_FIELD("missing required field."),
    RECORD_ALREADY_EXSITS("record already exsits."),
    INTERNAL_SERVER_ERROR("Internal server error");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
