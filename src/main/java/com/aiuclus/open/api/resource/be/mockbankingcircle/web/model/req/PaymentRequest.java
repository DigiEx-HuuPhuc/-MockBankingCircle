package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date requestExecutionDate;

  private String fxQuoteId;

  @NotNull private AccountRequest debtorAccount;

  @NotNull private AccountRequest debtorViban;

  private String debtorReference;

  private String debtorNarrativeToSelf;

  @NotBlank private String currencyOfTransfer;

  @NotNull private AmountRequest amount;

  @NotBlank private String chargeBearer;

  @NotNull private RemittanceInformation remittanceInformation;

  private String creditorId;

  @NotNull private AccountRequest creditorAccount;

  private String creditorName;

  @NotNull private PostalAddress creditorAddress;

  private String clearingNetwork;

  private String purposeCode;
}
