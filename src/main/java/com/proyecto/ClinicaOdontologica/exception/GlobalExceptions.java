package com.proyecto.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesoExcepcionNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    };


    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesoExcepcionBadRequest(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({InternalServerErrorException.class})
    public ResponseEntity<String> procesoExcepcionInternalServer(InternalServerErrorException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler({OkException.class})
    public ResponseEntity<String> procesoExcepcionOk (OkException e) {
        return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
    }

}
