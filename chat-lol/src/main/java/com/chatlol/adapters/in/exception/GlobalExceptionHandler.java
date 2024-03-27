package com.chatlol.adapters.in.exception;

import com.chatlol.domain.exception.ChaompionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ChaompionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChaompionNotFoundException domainError){
        return ResponseEntity
                .unprocessableEntity()
                .body(new ApiError(domainError.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
        String message = "Ops! Ocorreu Um Erro Inesperado";
        logger.error(message, unexpectedError);
        return ResponseEntity
                .unprocessableEntity()
                .body(new ApiError("Ops! Ocorreu um erro inesperado!"));
    }
    public record ApiError(String message) {

        }
    
}
