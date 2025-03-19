package com.aiuclus.open.api.resource.be.mockbankingcircle.web.integration;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class WebhookPaymentRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:processPayment")
                .log("Received payment: ${body}")
                .delay(simple("${random(1000, 30000)}"))
                .to("direct:sendWebhook");
    }
}