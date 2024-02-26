package com.delivery.project.dto;

import lombok.Data;

@Data
public class OrderDetails {

     private String packageName;
     private String postalCode;
     private String streetName;
     private String receiverName;
     private String packageSize;

}