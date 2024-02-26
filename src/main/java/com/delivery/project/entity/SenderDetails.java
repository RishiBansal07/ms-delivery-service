package com.delivery.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class SenderDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique=true)
    private String employeeId;

    @NotNull
    private String orderIdOfPackage;

}
