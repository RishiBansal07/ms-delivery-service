package com.delivery.project.services;

import com.delivery.project.entity.EmployeeDetails;
import com.delivery.project.entity.SenderDetails;

import java.util.List;

public interface SenderService {
    List<SenderDetails> searchByEmployeeId(String id);

    SenderDetails searchByOrderId(String id);

    String updateOrderId(String employeeIdSender, String orderId);

}
