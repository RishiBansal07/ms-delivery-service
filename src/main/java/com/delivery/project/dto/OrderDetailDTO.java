package com.delivery.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private String dateOfRegistration;
    private String packageStatus;
    private String dateOfReceipt;
}
