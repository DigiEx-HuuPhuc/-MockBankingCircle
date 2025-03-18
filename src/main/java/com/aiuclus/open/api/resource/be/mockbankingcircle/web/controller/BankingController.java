package com.aiuclus.open.api.resource.be.mockbankingcircle.web.controller;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.PaymentRequest;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.resp.PaymentResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/baking-circle")
public class BankingController {

  @PostMapping("/payment")
  public ResponseEntity<?> makePayment(@RequestBody PaymentRequest request) {
    return ResponseEntity.ok(
            PaymentResp.builder()
                    .paymentId(UUID.randomUUID().toString())
                    .status("PendingProcessing")
                    .build()
    );
  }
}
