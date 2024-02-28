package com.delivery.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.delivery.project.entities.EmployeeDetails;
import com.delivery.project.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void saveEmployeeDetails_ShouldCallRepositorySave() {
        // Arrange
        EmployeeDetails employeeDetails = new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem");

        // Act
        employeeService.saveEmployeeDetails(employeeDetails);

        // Assert
        verify(employeeRepository, times(1)).save(employeeDetails);
    }

    @Test
    void getListOfEmployee_ShouldReturnListOfEmployeeDetails() {
        // Arrange
        List<EmployeeDetails> employeeDetailsList = Collections.singletonList(new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem"));
        when(employeeRepository.findAll()).thenReturn(employeeDetailsList);

        // Act
        List<EmployeeDetails> result = employeeService.getListOfEmployee();

        // Assert
        assertEquals(employeeDetailsList, result);
    }

    @Test
    void searchByEmployeeId_ShouldReturnEmployeeDetails() {
        // Arrange
        Long employeeId = 123L;
        EmployeeDetails employeeDetails = new EmployeeDetails(2L, 112L, "CBMouli", "11000G", "Harleem");
        when(employeeRepository.searchByEmployeeId(employeeId)).thenReturn(employeeDetails);

        // Act
        EmployeeDetails result = employeeService.searchByEmployeeId(employeeId);

        // Assert
        assertEquals(employeeDetails, result);
    }
}

