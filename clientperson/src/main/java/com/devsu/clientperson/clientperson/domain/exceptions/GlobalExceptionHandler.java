package com.devsu.clientperson.clientperson.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.devsu.clientperson.clientperson.domain.models.ErrorMessage;

import java.util.Map;
import java.util.HashMap;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });        
        ErrorMessage error = ErrorMessage.builder().code(HttpStatus.BAD_REQUEST.toString()).message(errors.toString()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(Exception exception) {
        ErrorMessage error = ErrorMessage.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.toString()).message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> handlerNoHandlerFoundException(NoHandlerFoundException  exception) {
        ErrorMessage error = ErrorMessage.builder().code(HttpStatus.NOT_FOUND.toString()).message(exception.getMessage()).build();        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> requestExceptionHandler(ResourceNotFoundException exception) {
        ErrorMessage error = ErrorMessage.builder().code(HttpStatus.NOT_FOUND.toString()).message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BussinessException.class)
    public ResponseEntity<ErrorMessage> bussinessExceptionHandler(BussinessException e) {
        ErrorMessage error = ErrorMessage.builder().code(HttpStatus.CONFLICT.toString()).message(e.getMessage()).build();        
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
