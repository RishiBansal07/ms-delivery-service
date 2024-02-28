package com.delivery.project.services;

import com.delivery.project.entities.EmployeeDetails;
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

    /**
     *
     * @param employeeDetails
     */
    @Override
    public void saveEmployeeDetails(EmployeeDetails employeeDetails) {
        employeeRepository.save(employeeDetails);
    }

    /**
     *
     * @return
     */
    @Override
    public List<EmployeeDetails> getListOfEmployee() {
        return employeeRepository.findAll();
    }

    /**
     *
     * @param employeeDd
     * @return
     */
    @Override
    public EmployeeDetails searchByEmployeeId(Long employeeDd) {
        return employeeRepository.searchByEmployeeId(employeeDd);
    }
}



