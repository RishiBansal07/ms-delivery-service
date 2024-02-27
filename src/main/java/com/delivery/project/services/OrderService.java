package com.delivery.project.services;

import com.delivery.project.dto.Order;
import com.delivery.project.dto.OrderDetailDTO;
import com.delivery.project.dto.PlacedOrderDTO;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entity.EmployeeDetails;
import com.delivery.project.entity.SenderDetails;
import com.delivery.project.exceptions.OrderServiceException;
import com.delivery.project.utility.UUIDExtractor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UUIDExtractor uuidExtractor;
    @Autowired
    private SenderService senderService;

    public void createOrderDetails(ShippingOrderDTO shippingOrder) {

        EmployeeDetails employeeDetails = employeeService.searchByEmployeeId(shippingOrder.getEmployeeIdReceiver());
        Order order = new Order();
        order.setPackageName(shippingOrder.getNameOfThePackage());
        order.setPackageSize(shippingOrder.getWeightOfThePackage());
        order.setPostalCode(employeeDetails.getPostalCode());
        order.setStreetName(employeeDetails.getStreetName());
        order.setReceiverName(employeeDetails.getEmployeeName());

        String orderIdHeader = postCall(order);
        String orderId = uuidExtractor.extraction(orderIdHeader);

        senderService.updateOrderId(shippingOrder.getEmployeeIdSender(), orderId);
    }

    private String postCall(Order order) {
        // method to set the value
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> requestEntity = new HttpEntity<>(order, headers);
        ResponseEntity<Order> response;
        // try catch with Exception
        try {
            response = new RestTemplate().postForEntity("http://localhost:8443/shippingOrders", requestEntity, Order.class);
        } catch (Exception e) {
            throw new OrderServiceException(e.getMessage());
        }
        // End

        String orderIdHeader = String.valueOf(response.getHeaders().getLocation());
        return orderIdHeader;
    }

    public List<PlacedOrderDTO> getOrderDetailsOfEmployee(String employeeId) {
        List<SenderDetails> senderDetailsList = senderService.searchByEmployeeId(employeeId);
        List<PlacedOrderDTO> placedOrderDTOList = new ArrayList<>();
        for (SenderDetails i : senderDetailsList) {
            placedOrderDTOList.add(getCall(i.getOrderIdOfPackage()));
        }
        return placedOrderDTOList;
    }

    public OrderDetailDTO getOrderDetail(String orderId) {
        PlacedOrderDTO placedOrderDTO = getCall(orderId);
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setDateOfRegistration(getDateOfRegistration(orderId));
        if (placedOrderDTO.getOrderStatus().equals("DELIVERED")){
            orderDetailDTO.setDateOfReceipt(placedOrderDTO.getActualDeliveryDateTime());
        }
        return orderDetailDTO;
    }

    private String getDateOfRegistration(String orderId) {
        SenderDetails senderDetails = senderService.searchByOrderId(orderId);
        return senderDetails.getPackageRegisteredDate().toString();
    }

    private PlacedOrderDTO getCall(String orderId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<PlacedOrderDTO> response = new RestTemplate().exchange("http://localhost:8443/shippingOrders/" + orderId, HttpMethod.GET, requestEntity, PlacedOrderDTO.class);
        return response.getBody();
    }
}
