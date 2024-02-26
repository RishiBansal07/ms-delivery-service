package com.delivery.project.configuration.feignClient;

import com.delivery.project.dto.OrderDetails;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shipping-order-service")
public interface ShippingOrderClient {

    @RequestLine("POST /shippingOrders")
    @Headers("Content-Type: application/json")
    String orderPackage(OrderDetails orderDetails);
}
