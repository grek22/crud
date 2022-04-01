package com.grek.alcohol.configuratiuon;

import com.grek.alcohol.user.exception.EmailAlreadyInUseException;
import com.grek.alcohol.user.exception.UserNotFoundException;
import com.grek.alcohol.validation.exception.AgeIsLessThan18;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AgeIsLessThan18.class)
    protected ResponseEntity<String> handleAgeIsLessThan18Exception(AgeIsLessThan18 ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyInUseException.class)
    protected ResponseEntity<String> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
