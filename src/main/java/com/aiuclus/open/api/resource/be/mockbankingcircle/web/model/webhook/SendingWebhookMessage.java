package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class SendingWebhookMessage {
    private UUID id;
    private String topic;
    private ScheduleType scheduleType;
    private Instant triggerTime;
    private Payload data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class Payload {
        private Integer retryCount;
        private UUID entityId;
        private UUID webHookEventId;
        private UUID registeredWebhookId;
        private Object requestBody;
    }
}
