package com.aiuclus.open.api.resource.be.mockbankingcircle.web.service;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.AccountEntity;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.BeneficiaryEntity;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.PaymentEntity;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.PaymentRequest;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

//    public PaymentEntity createPayment(PaymentRequest request) {
//        PaymentEntity payment = mapToEntity(request);
////        var saved = paymentRepository.save(payment );
//        AccountEntity account = AccountEntity.fromRequest(request.getDebtorAccount());
//        var beneficiary = BeneficiaryEntity.fromRequest(request.getBeneficiaryRequest());
////        beneficiary.setPayment(payment);
////        account.setPayment(payment);
//        payment.setDebtorAccount(account);
//        payment.setBeneficiary(beneficiary);
//        payment.setStatus("PendingProcessing");
//        return paymentRepository.save(payment);
//    }

    public List<PaymentEntity> getPendingPayments() {
        return paymentRepository.findByStatus("PendingProcessing");
    }

    public PaymentEntity mapToEntity(PaymentRequest request) {
        return PaymentEntity.builder()
//                .paymentId(UUID.randomUUID().toString())
                .uniqueRequestId(UUID.randomUUID().toString())
                .requestedExecutionDate(request.getRequestExecutionDate())
                .status("PendingProcessing") // Mặc định là PendingProcessing khi tạo mới
                .chargeBearer(request.getChargeBearer())
                .clearingNetwork(request.getClearingNetwork())
                .amount(request.getAmount().getAmount())
                .currency(request.getCurrencyOfTransfer())
                .currencyOfTransfer(request.getCurrencyOfTransfer())
//                .debtorAccount(AccountEntity.fromRequest(request.getDebtorAccount()))
//                .beneficiary(BeneficiaryEntity.fromRequest(request.getBeneficiaryRequest()))
                .build();
    }

}

