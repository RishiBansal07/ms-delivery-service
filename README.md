import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

class DeliveryControllerTest {

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
        List<EmployeeDetails> employeeDetailsList = Collections.singletonList(new EmployeeDetails(/* employee details here */));
        when(employeeService.getListOfEmployee()).thenReturn(employeeDetailsList);

        // Act
        List<EmployeeDetails> result = deliveryController.findAllEmployee();

        // Assert
        assertEquals(employeeDetailsList, result);
    }

    @Test
    void orderPackage_ShouldCreateOrderDetails() {
        // Arrange
        ShippingOrderDTO shippingOrder = new ShippingOrderDTO(/* shipping order details here */);

        // Act
        deliveryController.orderPackage(shippingOrder);

        // Assert
        verify(orderService, times(1)).createOrderDetails(shippingOrder);
    }

    @Test
    void getOrderDetailsOfEmployee_ShouldReturnListOfPlacedOrderDTO() {
        // Arrange
        Long employeeId = 123L;
        List<PlacedOrderDTO> placedOrderDTOList = Collections.singletonList(new PlacedOrderDTO(/* order details here */));
        when(orderService.getOrderDetailsOfEmployee(employeeId)).thenReturn(placedOrderDTOList);

        // Act
        List<PlacedOrderDTO> result = deliveryController.getOrderDetailsOfEmployee(employeeId);

        // Assert
        assertEquals(placedOrderDTOList, result);
    }

    @Test
    void getOrderDetails_ShouldReturnOrderDetailDTO() {
        // Arrange
        String orderId = "ABC123";
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(/* order details here */);
        when(orderService.getOrderDetail(orderId)).thenReturn(orderDetailDTO);

        // Act
        OrderDetailDTO result = deliveryController.getOrderDetails(orderId);

        // Assert
        assertEquals(orderDetailDTO, result);
    }
}
