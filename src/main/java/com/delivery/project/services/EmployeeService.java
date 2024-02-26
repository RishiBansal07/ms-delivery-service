package com.delivery.project.services;

import com.delivery.project.entity.EmployeeDetails;

public interface EmployeeService {

    void saveEmployeeDetails(EmployeeDetails employeeDetails);

    EmployeeDetails searchByEmployeeId(String id);
}
