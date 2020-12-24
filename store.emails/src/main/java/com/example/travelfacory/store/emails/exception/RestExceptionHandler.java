package com.example.travelfacory.store.emails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchCampaignException.class)
    public ResponseEntity<String> NoSuchCampaignException() {
        logger.error("No such a Campaign, expected exception");
        return new ResponseEntity<>("No such a Campaign", HttpStatus.NOT_FOUND);
    }
}
