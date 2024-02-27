package com.delivery.project.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        if (log.isErrorEnabled()) {
            log.error("Unknown exception: " + ex.getMessage(), ex);
        }
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        if (log.isErrorEnabled()) {
            log.error("Constraint Validation Exception: " + ex.getMessage(), ex);
        }
        String message = ex.getConstraintViolations().stream().filter(Objects::nonNull).map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ErrorResponse("400", message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OrderServiceException.class})
    public ResponseEntity<ErrorResponse> orderServiceException(OrderServiceException ex) {
        if (log.isErrorEnabled()) {
            log.error("Server exception: " + ex.getMessage(), ex);
        }
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException ex) {
        if (log.isErrorEnabled()) {
            log.error("Illegal Argument exception: " + ex.getMessage(), ex);
        }
        ErrorResponse errorResponse = new ErrorResponse("400", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isErrorEnabled()) {
            log.error("Method Argument Not Valid Exception: " + ex.getMessage(), ex);
        }
        String errorMessage = this.formatError(ex.getBindingResult().getAllErrors());
        return new ResponseEntity(new ErrorResponse("400", errorMessage), HttpStatus.BAD_REQUEST);
    }

    private String formatError(List<ObjectError> allErrors) {
        return allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
    }
}
