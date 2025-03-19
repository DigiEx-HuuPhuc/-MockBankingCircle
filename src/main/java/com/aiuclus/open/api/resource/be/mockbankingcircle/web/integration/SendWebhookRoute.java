package com.aiuclus.open.api.resource.be.mockbankingcircle.web.integration;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.PaymentRequest;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.WebhookEventMessage;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.service.SendWebhookService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendWebhookRoute extends RouteBuilder {
    private final SendWebhookService sendWebhookService;

    @Override
    public void configure() {
        from("direct:sendWebhook")
                .process(sendWebhookService::sendWebhook)
                .log("Sending Webhook: ${body}")
                .toD("https://webhook.site/ab3b33e8-77da-479d-9ab5-7c7996536fbf");
    }

}
