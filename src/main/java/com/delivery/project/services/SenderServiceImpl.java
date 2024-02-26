package com.delivery.project.services;

import com.delivery.project.dto.OrderDetails;
import com.delivery.project.entity.SenderDetails;
import com.delivery.project.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService{

    private final SenderRepository senderRepository;

    @Override
    public SenderDetails searchByEmployeeId(String employeeDd) {
        return senderRepository.searchByEmployeeId(employeeDd);    }


    @Override
    public String updateOrderId(String employeeIdSender, String orderId) {
        senderRepository.searchByEmployeeId(employeeIdSender);
        SenderDetails senderDetails = new SenderDetails();
        senderDetails.setEmployeeId(employeeIdSender);
        senderDetails.setOrderIdOfPackage(orderId);
        senderRepository.save(senderDetails);
        return employeeIdSender;
    }
}
