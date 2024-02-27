package com.delivery.project.services;

import com.delivery.project.entity.EmployeeDetails;
import com.delivery.project.repository.EmployeeRepository;
import com.delivery.project.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void saveEmployeeDetails(EmployeeDetails employeeDetails) {
        employeeRepository.save(employeeDetails);
    }

    @Override
    public List<EmployeeDetails> getListOfEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDetails searchByEmployeeId(Long employeeDd) {
        return employeeRepository.searchByEmployeeId(employeeDd);    }
}



