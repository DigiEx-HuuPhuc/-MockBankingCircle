package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ScheduleType {
  SIMPLE_PLAN("SIM");

  @JsonValue private String code;

  public static ScheduleType lookup(String code) {
    if (code == null) {
      return null;
    }

    return Stream.of(values())
        .filter(each -> each.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
