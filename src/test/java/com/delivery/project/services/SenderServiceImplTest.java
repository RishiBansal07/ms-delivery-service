package com.delivery.project.services;

import com.delivery.project.entities.SenderDetails;
import com.delivery.project.repository.SenderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SenderServiceImplTest {

    private SenderServiceImpl senderService;
    private SenderRepository senderRepository;

    @BeforeEach
    void setUp() {
        senderRepository = mock(SenderRepository.class);
        senderService = new SenderServiceImpl(senderRepository);
    }

    @Test
    void searchByEmployeeId_ShouldReturnSenderDetailsList() {
        // Arrange
        Long employeeId = 123L;
        when(senderRepository.searchByEmployeeId(employeeId)).thenReturn(Collections.singletonList(new SenderDetails(1L, 111L, "5a0214f1-416d-4ef8-96f2-de4f9225771c", LocalDate.now())));

        // Act
        List<SenderDetails> result = senderService.searchByEmployeeId(employeeId);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void searchByOrderId_ShouldReturnSenderDetails() {
        // Arrange
        String orderId = "ABC123";
        when(senderRepository.searchByOrderId(orderId)).thenReturn(new SenderDetails(1L, 111L, "5a0214f1-416d-4ef8-96f2-de4f9225771c", LocalDate.now()));

        // Act
        SenderDetails result = senderService.searchByOrderId(orderId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void updateOrderId_ShouldUpdateOrderIdAndSave() {
        // Arrange
        Long employeeIdSender = 123L;
        String orderId = "ABC123";
        when(senderRepository.searchByEmployeeId(employeeIdSender)).thenReturn(new ArrayList<>());

        // Act
        Long result = senderService.updateOrderId(employeeIdSender, orderId);

        // Assert
        assertEquals(employeeIdSender, result);
    }
}