//package com.delivery.project.integration.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//class DeliveryControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // Add other dependencies like EmployeeServiceImpl, OrderService, etc. using @MockBean if needed
//
//    @Test
//    void testFindAllEmployeeEndpoint() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        // You can add more assertions based on the expected response
//    }
//
//    @Test
//    void testOrderPackageEndpoint() throws Exception {
//        // You need to construct a valid ShippingOrderDTO as JSON for the request body
//        String shippingOrderJson = "{\"employeeIdSender\": 111, \"employeeIdReceiver\": 112, \"nameOfThePackage\": \"Test Package\", \"weightOfThePackage\": 5.0}";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/orderPackage")
//                        .content(shippingOrderJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//        // You can add more assertions based on the expected response
//    }
//
//    @Test
//    void testGetOrderDetailsOfEmployeeEndpoint() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/getOrderDetailsOfEmployee/{employeeId}", 123)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        // You can add more assertions based on the expected response
//    }
//
//    @Test
//    void testGetOrderDetailsEndpoint() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/getOrderDetails/{orderId}", "ABC123")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        // You can add more assertions based on the expected response
//    }
//}
//
