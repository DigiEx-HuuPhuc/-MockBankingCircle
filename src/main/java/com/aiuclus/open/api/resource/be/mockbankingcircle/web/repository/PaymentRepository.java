package com.aiuclus.open.api.resource.be.mockbankingcircle.web.repository;

import com.aiuclus.open.api.resource.be.mockbankingcircle.web.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findByStatus(String status);
}
