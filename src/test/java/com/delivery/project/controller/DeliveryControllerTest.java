package com.delivery.project.controller;

import com.delivery.project.dto.OrderDetailDTO;
import com.delivery.project.dto.PlacedOrderDTO;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entities.EmployeeDetails;
import com.delivery.project.services.EmployeeServiceImpl;
import com.delivery.project.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeliveryControllerTest {

    private DeliveryController deliveryController;
    private EmployeeServiceImpl employeeService;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeServiceImpl.class);
        orderService = mock(OrderService.class);
        deliveryController = new DeliveryController(employeeService, orderService);
    }

    @Test
    void findAllEmployee_ShouldReturnListOfEmployeeDetails() {
        // Arrange
        List<EmployeeDetails> employeeDetailsList = Collections.singletonList(new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem"));
        when(employeeService.getListOfEmployee()).thenReturn(employeeDetailsList);

        // Act
        List<EmployeeDetails> result = deliveryController.findAllEmployee();

        // Assert
        assertEquals(employeeDetailsList, result);
    }

    @Test
    void orderPackage_ShouldCreateOrderDetails() {
        // Arrange
        ShippingOrderDTO shippingOrder = new ShippingOrderDTO("Birthday Gift", BigDecimal.valueOf(3000), 111L,112L);

        // Act
        deliveryController.orderPackage(shippingOrder);

        // Assert
        verify(orderService, times(1)).createOrderDetails(shippingOrder);
    }

    @Test
    void getOrderDetailsOfEmployee_ShouldReturnListOfPlacedOrderDTO() {
        // Arrange
        Long employeeId = 123L;
        List<PlacedOrderDTO> placedOrderDTOList = Collections.singletonList(new PlacedOrderDTO("5a0214f1-416d-4ef8-96f2-de4f9225771c", "Gift 1", "M", "11000G", "Harleem", "CBMouli", "IN_PROGRESS", "2024-02-29",null));
        when(orderService.getAllTheOrderDetailsPlacedByEmployee(employeeId)).thenReturn(placedOrderDTOList);

        // Act
        List<PlacedOrderDTO> result = deliveryController.getAllTheOrderDetailsPlacedByEmployee(employeeId);

        // Assert
        assertEquals(placedOrderDTOList, result);
    }

    @Test
    void getOrderDetails_ShouldReturnOrderDetailDTO() {
        // Arrange
        String orderId = "ABC123";
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO("2024-02-27", null,null);
        when(orderService.getOrderDetail(orderId)).thenReturn(orderDetailDTO);

        // Act
        OrderDetailDTO result = deliveryController.getOrderDetails(orderId);

        // Assert
        assertEquals(orderDetailDTO, result);
    }
}
