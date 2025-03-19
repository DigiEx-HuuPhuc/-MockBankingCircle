package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant;

public enum NotificationType {
  IncomingPaymentProcessed, // successfully funded
  OutgoingPaymentBooked, // payment is successfully booked
  OutgoingPaymentProcessed, // payment is successfully processed
  OutgoingPaymentRejected, // payment is rejected
  Reversed, // payment is reversed

  // payments cannot be executed due to insufficient balance.
  // The payment will be automatically executed when there are sufficient funds, or rejected after 2 days.
  MissingFunding
}
