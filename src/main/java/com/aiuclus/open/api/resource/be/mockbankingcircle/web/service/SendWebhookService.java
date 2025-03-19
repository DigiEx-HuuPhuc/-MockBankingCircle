package com.aiuclus.open.api.resource.be.mockbankingcircle.web.service;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.PaymentEntity;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.PaymentMapper;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.WebhookEventMessage;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.PaymentStatusType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendWebhookService {

    private final ObjectMapper objectMapper;

    public void sendWebhook(Exchange exchange) throws JsonProcessingException {
        PaymentEntity paymentEntity = exchange.getIn().getBody(PaymentEntity.class);
        if (paymentEntity == null) {
            log.error("PaymentEntity is null, cannot process webhook.");
            return;
        }

        // Randomize PaymentStatusType
        PaymentStatusType randomStatus = getRandomPaymentStatus();

        // Map PaymentEntity -> WebhookEventMessage
        WebhookEventMessage webhookEventMessage = PaymentMapper.mapToWebhookEvent(paymentEntity, randomStatus);
        String webhookUrl = "";


        // TODO: sending webhook to the URL
        log.info("Sending webhook with status {} to URL: {}", randomStatus, webhookUrl);
        log.debug("Webhook message: {}", webhookEventMessage);


        exchange.getIn().setBody(objectMapper.writeValueAsString(webhookEventMessage));
//        exchange.getIn().setHeader(Exchange.HTTP_URI, "https://webhook.site/ab3b33e8-77da-479d-9ab5-7c7996536fbf");
    }

    private PaymentStatusType getRandomPaymentStatus() {
        var statuses = List.of(PaymentStatusType.Rejected, PaymentStatusType.Rejected);
        return statuses.get(ThreadLocalRandom.current().nextInt(statuses.size()));
    }
}
