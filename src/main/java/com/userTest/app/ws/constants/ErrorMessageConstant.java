package com.userTest.app.ws.constants;

public final class ErrorMessageConstant {
    public static final String VALIDATION_ERROR_MSG = "Request failed due to a validation error";

    public static final String VALIDATION_MANDATORY_MSG = "This field is mandatory";

    public static final String VALIDATION_COUNTRY_MSG = "France is the only valid value";

    public static final String VALIDATION_BIRTHDAY_MSG = "Only adult users accepted (+18)";

    public static final String INVALID_FORMAT_ERROR_MSG = "Request failed due to an invalid format error";

    public static final String ALREADY_EXIST_DATA_ERROR_MSG = "Request failed due to a conflict when trying to persist already exist data";

    public static final String DATA_NOT_FOUND_ERROR_MSG = "Request failed due to a not found data error";


    private ErrorMessageConstant() {
        throw new UnsupportedOperationException("This is a class contains only constants");
    }

}
