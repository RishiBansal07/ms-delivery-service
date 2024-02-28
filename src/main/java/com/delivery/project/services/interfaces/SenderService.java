package com.delivery.project.services.interfaces;

import com.delivery.project.entities.SenderDetails;

import java.util.List;

public interface SenderService {
    List<SenderDetails> searchByEmployeeId(Long id);
    SenderDetails searchByOrderId(String id);
    Long updateOrderId(Long employeeIdSender, String orderId);

}
