package com.delivery.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class SenderDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String employeeId;

    @NotNull
    @Column(unique=true)
    private String orderIdOfPackage;

    private LocalDate packageRegisteredDate;

}
