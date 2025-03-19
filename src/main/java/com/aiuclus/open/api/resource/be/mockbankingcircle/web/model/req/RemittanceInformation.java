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
public class RemittanceInformation {
  private String line1;
  private String line2;
  private String line3;
  private String line4;
}
