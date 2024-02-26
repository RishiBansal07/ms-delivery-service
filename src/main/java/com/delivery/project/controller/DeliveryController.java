package com.delivery.project.controller;


import com.delivery.project.Utility.UUIDExtractor;
import com.delivery.project.dto.OrderDetails;
import com.delivery.project.dto.PlacedOrder;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.dto.ShippingOrderDetailsDTO;
import com.delivery.project.entity.EmployeeDetails;
import com.delivery.project.entity.SenderDetails;
import com.delivery.project.services.EmployeeServiceImpl;
import com.delivery.project.services.SenderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/bank/deliveryService")
@RequiredArgsConstructor
public class DeliveryController {

//    private final EmployeeServiceImpl employeeService;
//    private final SenderServiceImpl senderService;
//    private final ShippingOrderClient shippingOrderClient;
//    //private final ShippingOrderDetailsClient shippingOrderDetailsClient;
//
//    @PostMapping("/orderPackage")
//    public ResponseEntity<String> orderPackage(@RequestBody ShippingOrderDTO shippingOrder) {
//        EmployeeDetails employeeDetails = employeeService.searchByEmployeeId(shippingOrder.getEmployeeIdReceiver());
//
//        OrderDetails orderDetails = new OrderDetails();
//        orderDetails.setPackageName(shippingOrder.getNameOfThePackage());
//        orderDetails.setPackageSize(shippingOrder.getWeightOfThePackage());
//        orderDetails.setPostalCode(employeeDetails.getPostalCode());
//        orderDetails.setStreetName(employeeDetails.getStreetName());
//        orderDetails.setReceiverName(employeeDetails.getEmployeeName());
//
//        String response = shippingOrderClient.orderPackage(orderDetails);
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping("/getOrderDetails/{employeeId}")
//    public ResponseEntity<ShippingOrderDetailsDTO> getOrderDetails(@PathVariable String employeeId) {
//        SenderDetails senderDetails = senderService.searchByEmployeeId(employeeId);
//        ShippingOrderDetailsDTO response = shippingOrderClient.getOrderDetails(senderDetails.getOrderIdOfPackage());
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//}

    private final EmployeeServiceImpl employeeService;

    private final SenderServiceImpl senderService;

    private final UUIDExtractor uuidExtractor;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDetails> findAllPets() {
        return employeeService.getListOfEmployee();
    }

    @PostMapping(value = "/orderPackage", produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDetails> orderPackage(@Valid @RequestBody ShippingOrderDTO shippingOrder) {
        EmployeeDetails employeeDetails = employeeService.searchByEmployeeId(shippingOrder.getEmployeeIdReceiver());
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setPackageName(shippingOrder.getNameOfThePackage());
        orderDetails.setPackageSize(shippingOrder.getWeightOfThePackage());
        orderDetails.setPostalCode(employeeDetails.getPostalCode());
        orderDetails.setStreetName(employeeDetails.getStreetName());
        orderDetails.setReceiverName(employeeDetails.getEmployeeName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderDetails> requestEntity = new HttpEntity<>(orderDetails, headers);
        ResponseEntity<OrderDetails> response = new RestTemplate().postForEntity("http://localhost:8443/shippingOrders", requestEntity, OrderDetails.class);
        String orderId = String.valueOf(response.getHeaders().getLocation());
        System.out.println(orderId);
        uuidExtractor.extraction(orderId);
        senderService.updateOrderId(shippingOrder.getEmployeeIdSender(), orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // TODO
    @GetMapping("/getOrderDetailsOfEmployee/{employeeId}")
    public ResponseEntity<ShippingOrderDetailsDTO> getOrderDetailsOfEmployee(@PathVariable String employeeId) {
        SenderDetails senderDetails = senderService.searchByEmployeeId(employeeId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<ShippingOrderDetailsDTO> response = new RestTemplate().exchange("https://localhost:8443/shippingOrders/" + senderDetails.getOrderIdOfPackage(), HttpMethod.GET, requestEntity, ShippingOrderDetailsDTO.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/getOrderDetails/{orderId}")
    public ResponseEntity<PlacedOrder> getOrderDetails(@PathVariable String orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<PlacedOrder> response = new RestTemplate().exchange("http://localhost:8443/shippingOrders/" + orderId, HttpMethod.GET, requestEntity, PlacedOrder.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}

//        1. Create the end points.
//        2. DB- USER table with all the details - Employee id.
//        3. DB- Employeer SENDER ID associated with order id.