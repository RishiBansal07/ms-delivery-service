//package com.delivery.project.configuration.feignClient;
//
//import com.delivery.project.dto.OrderDetails;
//import com.delivery.project.dto.ShippingOrderDetailsDTO;
//import feign.Headers;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "shipping-order-service")
//public interface ShippingOrderClient {
//
//    @PostMapping("/shippingOrders")
//    @Headers("Content-Type: application/json")
//    String orderPackage(@RequestBody OrderDetails orderDetails);
//
//    @GetMapping("/shippingOrders/{orderId}")
//    ShippingOrderDetailsDTO getOrderDetails(@PathVariable("orderId") String orderId);
//}
