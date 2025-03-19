package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req;

import jakarta.validation.constraints.NotBlank;
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
public class PostalAddress {
  @NotBlank private String line1;
  private String line2;
  private String line3;
}
