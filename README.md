import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
        ShippingOrderDTO shippingOrder = new ShippingOrderDTO(/* shipping order details here */);
        EmployeeDetails employeeDetails = new EmployeeDetails(/* employee details here */);
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
        List<SenderDetails> senderDetailsList = Collections.singletonList(new SenderDetails(/* sender details here */));
        when(senderService.searchByEmployeeId(employeeId)).thenReturn(senderDetailsList);
        when(orderService.getCall(anyString())).thenReturn(new PlacedOrderDTO(/* placed order details here */));

        // Act
        List<PlacedOrderDTO> result = orderService.getOrderDetailsOfEmployee(employeeId);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void getOrderDetail_ShouldReturnOrderDetailDTO() {
        // Arrange
        String orderId = "ABC123";
        PlacedOrderDTO placedOrderDTO = new PlacedOrderDTO(/* placed order details here */);
        when(orderService.getCall(orderId)).thenReturn(placedOrderDTO);
        when(senderService.searchByOrderId(orderId)).thenReturn(new SenderDetails(/* sender details here */));

        // Act
        OrderDetailDTO result = orderService.getOrderDetail(orderId);

        // Assert
        assertNotNull(result);
    }
}

class SenderServiceImplTest {

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
        when(senderRepository.searchByEmployeeId(employeeId)).thenReturn(Collections.singletonList(new SenderDetails(/* sender details here */)));

        // Act
        List<SenderDetails> result = senderService.searchByEmployeeId(employeeId);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void searchByOrderId_ShouldReturnSenderDetails() {
        // Arrange
        String orderId = "ABC123";
        when(senderRepository.searchByOrderId(orderId)).thenReturn(new SenderDetails(/* sender details here */));

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
