package com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain;


import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.WebhookEventMessage;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.ClassificationType;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.NotificationType;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.PaymentStatusType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class PaymentMapper {

    public static WebhookEventMessage mapToWebhookEvent(PaymentEntity paymentEntity, PaymentStatusType status) {
        return WebhookEventMessage.builder()
                .notifications(List.of(buildNotification(paymentEntity, status)))
                .build();
    }

    private static WebhookEventMessage.Notification buildNotification(PaymentEntity paymentEntity, PaymentStatusType status) {
        return WebhookEventMessage.Notification.builder()
                .subscriptionId(UUID.randomUUID())
                .subscriptionEventId(UUID.randomUUID())
                .eventId(UUID.randomUUID())
                .timestamp(ZonedDateTime.now())
                .notificationType(NotificationType.OutgoingPaymentProcessed)
                .payment(buildPayment(paymentEntity, status))
                .build();
    }

    private static WebhookEventMessage.Notification.Payment buildPayment(PaymentEntity paymentEntity, PaymentStatusType status) {
        return WebhookEventMessage.Notification.Payment.builder()
                .paymentId(UUID.randomUUID())
                .transactionReference(paymentEntity.getUniqueRequestId())
                .classification(ClassificationType.Outgoing) // Giả định luồng đi ra
                .status(status)
                .processedTimestamp(Instant.now())
                .lastChangedTimestamp(ZonedDateTime.now())
                .debtorInformation(buildDebtorInformation(paymentEntity))
                .transfer(buildTransfer(paymentEntity))
                .valueDate(paymentEntity.getRequestedExecutionDate())
                .creditorInformation(buildCreditorInformation(paymentEntity))
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.DebtorInformation buildDebtorInformation(PaymentEntity paymentEntity) {
        AccountEntity debtor = paymentEntity.getDebtorAccount();
        return WebhookEventMessage.Notification.Payment.DebtorInformation.builder()
                .debitAmount(buildAmount(paymentEntity.getCurrency(), paymentEntity.getAmount()))
                .instructedDate(ZonedDateTime.now())
                .debitValueDate(ZonedDateTime.now())
                .instruction(buildInstruction(paymentEntity))
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.DebtorInformation.Instruction buildInstruction(PaymentEntity paymentEntity) {
        AccountEntity debtor = paymentEntity.getDebtorAccount();
        BeneficiaryEntity beneficiary = paymentEntity.getBeneficiary();
        return WebhookEventMessage.Notification.Payment.DebtorInformation.Instruction.builder()
                .debtorViban(buildAccount(debtor))
                .debtorReference("Reference Information")
                .currencyOfTransfer(paymentEntity.getCurrencyOfTransfer())
                .requestedExecutionDate(ZonedDateTime.now())
                .creditorId(UUID.randomUUID().toString())
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.Transfer buildTransfer(PaymentEntity paymentEntity) {
        return WebhookEventMessage.Notification.Payment.Transfer.builder()
                .debtorAccount(buildAccount(paymentEntity.getDebtorAccount()))
                .creditorAccount(buildAccountFrom(paymentEntity.getBeneficiary()))
                .debtorName("Debtor Name")
//        paymentEntity.getBeneficiary().getName()
                .creditorName("sbc")
                .amount(buildAmount(paymentEntity.getCurrency(), paymentEntity.getAmount()))
                .chargeBearer(paymentEntity.getChargeBearer())
                .valueDate(paymentEntity.getRequestedExecutionDate())
                .debtorAddress(buildAddress(paymentEntity.getDebtorAccount()))
                .creditorAddress(buildAddress(paymentEntity.getBeneficiary()))
                .remittanceInformation(buildRemittanceInformation())
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.CreditorInformation buildCreditorInformation(PaymentEntity paymentEntity) {
        return WebhookEventMessage.Notification.Payment.CreditorInformation.builder()
                .creditAmount(buildAmount(paymentEntity.getCurrency(), paymentEntity.getAmount()))
                .creditValueDate(ZonedDateTime.now())
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.Amount buildAmount(String currency, Double amount) {
        return WebhookEventMessage.Notification.Payment.Amount.builder()
                .currency(currency)
                .amount(amount)
                .build();
    }

    private static WebhookEventMessage.Notification.Payment.Account buildAccount(AccountEntity account) {
        if (account == null) return null;
        return WebhookEventMessage.Notification.Payment.Account.builder()
                .account(account.getIban())
                .financialInstitution(account.getBic())
                .country(account.getCountry())
                .build();
    }


    private static WebhookEventMessage.Notification.Payment.Account buildAccountFrom(BeneficiaryEntity account) {
        if (account == null) return null;
        return WebhookEventMessage.Notification.Payment.Account.builder()
                .account(account.getIban())
                .financialInstitution(account.getBic())
                .country(account.getCountry())
                .build();
    }


    private static WebhookEventMessage.Notification.Payment.Address buildAddress(Object entity) {
        if (entity instanceof AccountEntity account) {
            return WebhookEventMessage.Notification.Payment.Address.builder()
                    .line1(account.getCountry())
                    .line2("Bank Branch")
                    .line3("City Info")
                    .build();
        }
        if (entity instanceof BeneficiaryEntity beneficiary) {
            return WebhookEventMessage.Notification.Payment.Address.builder()
                    .line1(beneficiary.getAddress())
                    .line2("Recipient Info")
                    .line3("Region")
                    .build();
        }
        return null;
    }

    private static WebhookEventMessage.Notification.Payment.RemittanceInformation buildRemittanceInformation() {
        return WebhookEventMessage.Notification.Payment.RemittanceInformation.builder()
                .line1("Invoice Payment")
                .line2("For Service")
                .line3("2024-03-18")
                .line4("Reference XYZ")
                .build();
    }
}