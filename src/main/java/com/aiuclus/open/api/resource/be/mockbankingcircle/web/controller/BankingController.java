package com.aiuclus.open.api.resource.be.mockbankingcircle.web.controller;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.PaymentEntity;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.PaymentRequest;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.resp.PaymentResp;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/banking-circle")
@RequiredArgsConstructor
public class BankingController {

  private final PaymentService paymentService;
  private final ProducerTemplate producerTemplate;

  @PostMapping("/payment")
  public ResponseEntity<?> makePayment(@RequestBody PaymentRequest request) {
    PaymentEntity payment = paymentService.mapToEntity(request);
    producerTemplate.sendBody("direct:processPayment", payment);
    return ResponseEntity.ok(
            PaymentResp.builder()
                    .paymentId(UUID.randomUUID().toString())
                    .status("PendingProcessing")
                    .build()
    );
  }
}
