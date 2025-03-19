package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountRequest {
  private String currency;
  private Double amount;
}
