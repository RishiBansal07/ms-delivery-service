package com.delivery.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShippingOrderDTO {

        private String nameOfThePackage;

        // todo
        // it should be S, M, L, XL
        private String weightOfThePackage;

        //todo
        //should be numeric
        private String employeeIdSender;


        //todo
        // should be numeric
        private String employeeIdReceiver;
}
