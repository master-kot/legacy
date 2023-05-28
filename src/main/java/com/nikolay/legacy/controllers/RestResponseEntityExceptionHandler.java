package com.nikolay.legacy.controllers;

import com.nikolay.legacy.dtos.ErrorDto;
import com.nikolay.legacy.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<ErrorDto> handleDataNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
