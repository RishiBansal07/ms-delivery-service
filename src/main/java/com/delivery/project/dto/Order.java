package com.delivery.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
     private String packageName;
     private String postalCode;
     private String streetName;
     private String receiverName;
     private String packageSize;
}
