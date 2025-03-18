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
public class AccountRequest {
  private String iban;
  private String accountNumber;
  private String bic;
  private String country;
}
