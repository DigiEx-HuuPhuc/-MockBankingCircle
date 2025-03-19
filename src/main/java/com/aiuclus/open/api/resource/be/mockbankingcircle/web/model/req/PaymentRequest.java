package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentRequest {
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date requestExecutionDate;
  private String uniqueRequestId;
  private String currency;
  private String currencyOfTransfer;
  private Double amount;
  private AccountRequest debtorAccount;
  private BeneficiaryRequest beneficiaryRequest;
  private String remittanceInformation;
  private String masterAccountIban;
  private String chargeBearer;
  private String clearingNetwork;
}
