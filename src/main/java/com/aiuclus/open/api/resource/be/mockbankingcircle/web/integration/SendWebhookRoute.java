package com.aiuclus.open.api.resource.be.mockbankingcircle.web.integration;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.service.SendWebhookService;
import lombok.RequiredArgsConstructor;
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
                .setHeader("Content-Type", constant("application/json"))
                .toD("http://localhost:8084/webhook/receive");
    }

}
