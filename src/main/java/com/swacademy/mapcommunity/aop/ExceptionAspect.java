package com.swacademy.mapcommunity.aop;

import com.swacademy.mapcommunity.domain.exception.PersistenceInternalException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {

    @Around("@annotation(PersistenceExceptionConverter)")
    public Object persistenceExceptionConverter(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (InvalidDataAccessApiUsageException |
                 DataRetrievalFailureException | EntityNotFoundException illegalArgumentException) {
            throw new IllegalArgumentException(illegalArgumentException.getMessage());
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceInternalException(dataAccessException.getMessage());
        }

    }
}
