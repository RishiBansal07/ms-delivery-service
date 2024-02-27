package com.delivery.project.services;

import com.delivery.project.entities.SenderDetails;
import com.delivery.project.repository.SenderRepository;
import com.delivery.project.services.interfaces.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService {

    private final SenderRepository senderRepository;

    @Override
    public List<SenderDetails> searchByEmployeeId(Long employeeDd) {
        return senderRepository.searchByEmployeeId(employeeDd);
    }
    /**
     * @param id
     * @return
     */
    @Override
    public SenderDetails searchByOrderId(String orderId) {
        return senderRepository.searchByOrderId(orderId);
    }


    @Override
    public Long updateOrderId(Long employeeIdSender, String orderId) {
        senderRepository.searchByEmployeeId(employeeIdSender);
        SenderDetails senderDetails = new SenderDetails();
        senderDetails.setEmployeeId(employeeIdSender);
        senderDetails.setOrderIdOfPackage(orderId);
        senderDetails.setPackageRegisteredDate(LocalDate.now());
        senderRepository.save(senderDetails);
        return employeeIdSender;
    }
}
