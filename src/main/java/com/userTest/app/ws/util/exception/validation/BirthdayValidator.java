package com.userTest.app.ws.util.exception.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class BirthdayValidator implements ConstraintValidator<BirthdayConstraint, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext cxt) {

        return birthdate != null && birthdate.isBefore(LocalDate.now().minusYears(18));
    }

}