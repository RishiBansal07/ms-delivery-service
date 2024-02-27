package com.delivery.project.services;

import com.delivery.project.entity.SenderDetails;
import com.delivery.project.repository.SenderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService{

    private final SenderRepository senderRepository;

    @Override
    public List<SenderDetails> searchByEmployeeId(String employeeDd) {
        return senderRepository.searchByEmployeeId(employeeDd);    }

    /**
     * @param id
     * @return
     */
    @Override
    public SenderDetails searchByOrderId(String orderId) {
        return senderRepository.searchByOrderId(orderId);
    }


    @Override
    public String updateOrderId(String employeeIdSender, String orderId) {
        senderRepository.searchByEmployeeId(employeeIdSender);
        SenderDetails senderDetails = new SenderDetails();
        senderDetails.setEmployeeId(employeeIdSender);
        senderDetails.setOrderIdOfPackage(orderId);
        senderDetails.setPackageRegisteredDate(LocalDate.now());
        senderRepository.save(senderDetails);
        return employeeIdSender;
    }
}
