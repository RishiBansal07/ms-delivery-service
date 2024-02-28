package com.delivery.project.controller;


import com.delivery.project.dto.OrderDetailDTO;
import com.delivery.project.dto.PlacedOrderDTO;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entities.EmployeeDetails;
import com.delivery.project.services.EmployeeServiceImpl;
import com.delivery.project.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/deliveryService")
@Tag(name = "Delivery-Controller", description = "(Version 1) Controller that basically does the delivery operations for the Bank emoployees!")
@RequiredArgsConstructor
public class DeliveryController {

    private final EmployeeServiceImpl employeeService;
    private final OrderService orderService;

    @Operation(description = "Get list of the employees with their ID and address ")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @GetMapping("/listOfEmployees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDetails> findAllEmployee() {
        return employeeService.getListOfEmployee();
    }

    @Operation(description = "Place a new order for delivery")
    @ApiResponse(responseCode = "204", description = "Successful Operation")
    @PostMapping(value = "/orderPackage", produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void orderPackage(@Valid @RequestBody ShippingOrderDTO shippingOrder) {
        orderService.createOrderDetails(shippingOrder);
    }

    @Operation(description = "Details of all the order placed by Employee")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @GetMapping("/getOrderDetailsOfEmployee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlacedOrderDTO> getAllTheOrderDetailsPlacedByEmployee(@PathVariable Long employeeId) {
        return orderService.getAllTheOrderDetailsPlacedByEmployee(employeeId);
    }

    @Operation(description = "Get the details of specific order")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @GetMapping("/getOrderDetails/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetailDTO getOrderDetails(@PathVariable String orderId) {
        return orderService.getOrderDetail(orderId);
    }
}