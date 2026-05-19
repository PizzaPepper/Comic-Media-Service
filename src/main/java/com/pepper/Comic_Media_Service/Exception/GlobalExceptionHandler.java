package com.pepper.Comic_Media_Service.Exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pepper.Comic_Media_Service.DTO.Response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        log.error("Resource Not Found - Reason: {}", ex.getMessage());
        
        return ResponseEntity
                .status(404)
                .body(ErrorResponse.builder().status(404).body(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(ResourceAlreadyExistsException ex) {

        log.error("Resource Already Exists - Reason: {}", ex.getMessage());

        return ResponseEntity
                .status(409)
                .body(ErrorResponse.builder().status(409).body(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {

        log.error("Bad Request - Reason: {}", ex.getMessage());

        return ResponseEntity
                .status(400)
                .body(ErrorResponse.builder().status(400).body(ex.getMessage()).timestamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

        log.error("Error - Reason: {}", ex.getMessage());

        return ResponseEntity
                .status(500)
                .body(ErrorResponse.builder().status(500).body("Internal server error").timestamp(LocalDateTime.now())
                        .build());
    }
}
