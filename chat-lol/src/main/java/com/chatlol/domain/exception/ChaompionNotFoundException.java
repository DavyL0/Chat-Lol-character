package com.chatlol.domain.exception;

public class ChaompionNotFoundException extends RuntimeException{
    public ChaompionNotFoundException(Long championId) {
        super("Champion %d not found." . formatted(championId));
    }
}
