package com.swacademy.mapcommunity.domain.exception;

/**
 * <p> When an exception is thrown in the data layer.
 * Such as {@link org.springframework.dao.PessimisticLockingFailureException PessimisticLockingFailureException},
 * {@link org.springframework.dao.OptimisticLockingFailureException OptimisticLockingFailureException},
 * {@link org.springframework.dao.DataIntegrityViolationException DataIntegrityViolationException} etc.</p>
 * <p> This exception plays a role in wrapping exceptions that occur in JDBC, JPA, or any other data layer
 * implementation, so that the server can handle them appropriately. </p>
 */
public class InternalPersistenceException extends RuntimeException {
    public InternalPersistenceException() {}
    public InternalPersistenceException(String message) {
        super(message);
    }
    public InternalPersistenceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
