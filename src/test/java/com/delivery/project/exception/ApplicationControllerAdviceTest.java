package com.delivery.project.exception;

import com.delivery.project.exceptions.ErrorResponse;
import com.delivery.project.exceptions.GlobalExceptionHandler;
import com.delivery.project.exceptions.OrderServiceException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ApplicationControllerAdviceTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    void shouldHandleOrderServiceException() {
        OrderServiceException exception = new OrderServiceException("Server exception");
        ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.orderServiceException(exception);
        ErrorResponse responseBody = errorResponse.getBody();
        assertNotNull(responseBody);
        assertThat(responseBody.getCode()).isEqualTo("500");
        assertThat(responseBody.getDescription()).isEqualTo("Server exception");
    }

    @Test
    void shouldHandleConstraintViolationException() {
        ConstraintViolationException exception = new ConstraintViolationException(
                "",
                Set.of(ConstraintViolationImpl.forParameterValidation(
                        null,
                        null,
                        null,
                        "Message",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)
                )
        );

        ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.handleConstraintViolationException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatusCode());
        assertNotNull(errorResponse.getBody());
        assertEquals("400", errorResponse.getBody().getCode());
        assertEquals("Message", errorResponse.getBody().getDescription());
    }
}