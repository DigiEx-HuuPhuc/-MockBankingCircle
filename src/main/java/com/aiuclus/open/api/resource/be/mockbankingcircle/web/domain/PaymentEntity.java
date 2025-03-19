package com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String uniqueRequestId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date requestedExecutionDate;

    private String status; // PendingProcessing, Processed, Rejected
    private String chargeBearer;
    private String clearingNetwork;
    private Double amount;
    private String currency;
    private String currencyOfTransfer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private AccountEntity debtorAccount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private BeneficiaryEntity beneficiary;
}
