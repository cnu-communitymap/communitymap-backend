package com.swacademy.mapcommunity.domain.exception;

public class InternalServerError extends RuntimeException {
    public InternalServerError() {}
    public InternalServerError(String message) {
        super(message);
    }
    public InternalServerError(String message, Throwable throwable) {
        super(message, throwable);
    }
}
