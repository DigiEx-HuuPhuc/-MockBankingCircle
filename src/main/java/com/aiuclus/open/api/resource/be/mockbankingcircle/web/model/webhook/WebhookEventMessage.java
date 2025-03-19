package com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.ClassificationType;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.NotificationType;
import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.webhook.constant.PaymentStatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WebhookEventMessage implements Serializable {
    private List<Notification> notifications;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notification {
        private UUID subscriptionId;
        private UUID subscriptionEventId;
        private UUID eventId;
        private ZonedDateTime timestamp;
        private NotificationType notificationType;
        private Payment payment;

        @Data
        @SuperBuilder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Payment {
            private UUID paymentId;
            private String transactionReference;
            private ClassificationType classification;
            private PaymentStatusType status;
            private List<Error> errors;
            private ZonedDateTime lastChangedTimestamp;
            private Instant processedTimestamp;
            private DebtorInformation debtorInformation;
            private Transfer transfer;
            private Date valueDate;
            private CreditorInformation creditorInformation;

            @Data
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Error {
                private String errorCode;
                private String errorText;
                private String propertyName;
                private Integer lineNumber;
                private Integer fieldNumber;
                private Boolean isOk;
            }

            @Data
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class DebtorInformation extends Information {
                private UUID paymentBulkId;
                private ZonedDateTime instructedDate;
                private Amount debitAmount;
                private ZonedDateTime debitValueDate;
                private Instruction instruction;

                @Data
                @SuperBuilder
                @NoArgsConstructor
                @AllArgsConstructor
                public static class Instruction extends Transfer {
                    private Account debtorViban;
                    private String debtorReference;
                    private String debtorNarrativeToSelf;
                    private String currencyOfTransfer;
                    private ZonedDateTime requestedExecutionDate;
                    private String creditorId;
                }
            }

            @Data
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Transfer {
                private Account debtorAccount;
                private String debtorName;
                private Address debtorAddress;
                private Amount amount;
                private Date valueDate;
                private String chargeBearer;
                private RemittanceInformation remittanceInformation;
                private Account creditorAccount;
                private String creditorName;
                private Address creditorAddress;
            }

            @Data
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class CreditorInformation extends Information {
                private Amount creditAmount;
                private ZonedDateTime creditValueDate;
            }

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Account {
                private String account;
                private String financialInstitution;
                private String country;
            }

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Amount {
                private String currency;
                private Double amount;
            }

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Address {
                private String line1;
                private String line2;
                private String line3;
            }

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class RemittanceInformation {
                private String line1;
                private String line2;
                private String line3;
                private String line4;
            }

            @Data
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Information {
                private UUID accountId;
                private Account account;
                private String vibanId;
                private Account viban;
                private Double fxRate;
            }
        }
    }
}
