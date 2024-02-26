package com.delivery.project.configuration.feignClient;

import com.delivery.project.dto.ShippingOrderDetailsDTO;
import feign.Param;
import feign.RequestLine;

public interface ShippingOrderDetailsClient {

    @RequestLine("GET /shippingOrders/{orderId}")
    ShippingOrderDetailsDTO getOrderDetails(@Param("orderId") String orderId);
}
