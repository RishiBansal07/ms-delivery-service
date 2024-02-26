package com.delivery.project.services;

import com.delivery.project.entity.EmployeeDetails;
import com.delivery.project.entity.SenderDetails;

public interface SenderService {
    SenderDetails searchByEmployeeId(String id);
}
