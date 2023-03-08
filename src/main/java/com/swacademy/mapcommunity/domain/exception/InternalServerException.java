package com.swacademy.mapcommunity.domain.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException() {}
    public InternalServerException(String message) {
        super(message);
    }
    public InternalServerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
