package com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.AccountRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String iban;
    private String accountNumber;
    private String bic;
    private String country;

    public static AccountEntity fromRequest(AccountRequest request) {
        return AccountEntity.builder()
                .iban(request.getIban())
                .accountNumber(request.getAccountNumber())
                .bic(request.getBic())
                .country(request.getCountry())
                .build();
    }
}
