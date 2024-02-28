package com.delivery.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.delivery.project.dto.OrderDetailDTO;
import com.delivery.project.dto.PlacedOrderDTO;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entities.EmployeeDetails;
import com.delivery.project.entities.SenderDetails;
import com.delivery.project.services.interfaces.EmployeeService;
import com.delivery.project.services.interfaces.SenderService;
import com.delivery.project.utility.UUIDExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

class OrderServiceTest {

    private OrderService orderService;
    private EmployeeService employeeService;
    private UUIDExtractor uuidExtractor;
    private SenderService senderService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
        uuidExtractor = mock(UUIDExtractor.class);
        senderService = mock(SenderService.class);
        orderService = new OrderService(employeeService, uuidExtractor, senderService);
    }

    @Test
    void createOrderDetails_ShouldCreateOrderAndInvokeDependencies() {
        // Arrange
        ShippingOrderDTO shippingOrder = new ShippingOrderDTO("Birthday Gift", BigDecimal.valueOf(3000), 111L,112L);
        EmployeeDetails employeeDetails = new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem");
        when(employeeService.searchByEmployeeId(shippingOrder.getEmployeeIdReceiver())).thenReturn(employeeDetails);
        when(uuidExtractor.extraction(anyString())).thenReturn("generatedOrderId");

        // Act
        orderService.createOrderDetails(shippingOrder);

        // Assert
        verify(senderService, times(1)).updateOrderId(anyLong(), eq("generatedOrderId"));
    }

    @Test
    void getOrderDetailsOfEmployee_ShouldReturnListOfPlacedOrderDTO() {
        // Arrange
        Long employeeId = 123L;
        List<SenderDetails> senderDetailsList = Collections.singletonList(new SenderDetails(1L, 111L, "5a0214f1-416d-4ef8-96f2-de4f9225771c", LocalDate.now()));
        when(senderService.searchByEmployeeId(employeeId)).thenReturn(senderDetailsList);
        when(orderService.getCall(anyString())).thenReturn(new PlacedOrderDTO("5a0214f1-416d-4ef8-96f2-de4f9225771c", "Gift 1", "M", "11000G", "Harleem", "CBMouli", "IN_PROGRESS", "2024-02-29",null));

        // Act
        List<PlacedOrderDTO> result = orderService.getAllTheOrderDetailsPlacedByEmployee(employeeId);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void getOrderDetail_ShouldReturnOrderDetailDTO() {
        // Arrange
        String orderId = "ABC123";
        PlacedOrderDTO placedOrderDTO = new PlacedOrderDTO("5a0214f1-416d-4ef8-96f2-de4f9225771c", "Gift 1", "M", "11000G", "Harleem", "CBMouli", "IN_PROGRESS", "2024-02-29",null);
        when(orderService.getCall(orderId)).thenReturn(placedOrderDTO);
        when(senderService.searchByOrderId(orderId)).thenReturn(new SenderDetails(/* sender details here */));

        // Act
        OrderDetailDTO result = orderService.getOrderDetail(orderId);

        // Assert
        assertNotNull(result);
    }
}

