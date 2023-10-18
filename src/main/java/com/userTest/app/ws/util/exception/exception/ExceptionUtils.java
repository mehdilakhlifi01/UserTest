package com.userTest.app.ws.util.exception.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


public final class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Map<String, String> getMappedFieldError(BindException e) {
        Map<String, String> data = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            data.put(error.getField(), error.getDefaultMessage());
        }
        return data;
    }

}
