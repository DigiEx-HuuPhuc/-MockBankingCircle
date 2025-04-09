package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResp {
  private String paymentId;
  private String transactionReference;
  private String concurrencyToken;
  private String classification;
  private String status;
  private String errors;
}
