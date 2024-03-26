package ru.netology.diplomproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.diplomproject.dto.ExceptionResponse;
import ru.netology.diplomproject.exceptions.ErrorFileException;
import ru.netology.diplomproject.exceptions.ErrorInputDataException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ErrorFileException.class)
    public ResponseEntity<ExceptionResponse> efHandler(ErrorFileException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorInputDataException.class)
    public ResponseEntity<ExceptionResponse> authHandler(ErrorInputDataException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), 401), HttpStatus.UNAUTHORIZED);
    }
}

