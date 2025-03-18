package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req;

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
public class BeneficiaryRequest {
  private String iban;
  private String name;
  private String address;
  private String bic;
  private String accountNumber;
  private String country;
}
