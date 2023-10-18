package com.userTest.app.ws.util.exception.exception;

import com.userTest.app.ws.util.exception.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.userTest.app.ws.constants.ErrorMessageConstant.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(DATA_NOT_FOUND_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handle(BindException e) {
        ErrorResponse response = new ErrorResponse(VALIDATION_ERROR_MSG, ExceptionUtils.getMappedFieldError(e));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
        ErrorResponse response = new ErrorResponse(INVALID_FORMAT_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handle(DataIntegrityViolationException e) {
        ErrorResponse response = new ErrorResponse(ALREADY_EXIST_DATA_ERROR_MSG, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
