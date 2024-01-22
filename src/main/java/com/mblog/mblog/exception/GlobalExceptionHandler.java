package com.mblog.mblog.exception;

import com.mblog.mblog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResonseNotFoundException(
            ResourceNotFoundException e,
            WebRequest webrequest
    ){
        ErrorDetails errordet = new ErrorDetails(e.getMessage(),new Date(),webrequest.getDescription(true));
        return new ResponseEntity<>(errordet, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
