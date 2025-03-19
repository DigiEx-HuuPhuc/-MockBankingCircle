package com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.model.req.BeneficiaryRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class BeneficiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String iban;
    private String name;
    private String address;
    private String bic;
    private String accountNumber;
    private String country;

    public static BeneficiaryEntity fromRequest(BeneficiaryRequest request) {
        return BeneficiaryEntity.builder()
                .iban(request.getIban())
                .name(request.getName())
                .address(request.getAddress())
                .bic(request.getBic())
                .accountNumber(request.getAccountNumber())
                .country(request.getCountry())
                .build();
    }
}
