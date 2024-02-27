package com.delivery.project.controller;


import com.delivery.project.dto.OrderDetailDTO;
import com.delivery.project.dto.PlacedOrderDTO;
import com.delivery.project.dto.ShippingOrderDTO;
import com.delivery.project.entities.EmployeeDetails;
import com.delivery.project.services.EmployeeServiceImpl;
import com.delivery.project.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/deliveryService")
@RequiredArgsConstructor
public class DeliveryController {

    private final EmployeeServiceImpl employeeService;
    private final OrderService orderService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDetails> findAllEmployee() {
        return employeeService.getListOfEmployee();
    }

    @PostMapping(value = "/orderPackage", produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void orderPackage(@Valid @RequestBody ShippingOrderDTO shippingOrder) {
        orderService.createOrderDetails(shippingOrder);
    }

    @GetMapping("/getOrderDetailsOfEmployee/{employeeId}")
    public List<PlacedOrderDTO> getOrderDetailsOfEmployee(@PathVariable Long employeeId) {
        return orderService.getOrderDetailsOfEmployee(employeeId);
    }

    @GetMapping("/getOrderDetails/{orderId}")
    public OrderDetailDTO getOrderDetails(@PathVariable String orderId) {
        return orderService.getOrderDetail(orderId);
    }
}