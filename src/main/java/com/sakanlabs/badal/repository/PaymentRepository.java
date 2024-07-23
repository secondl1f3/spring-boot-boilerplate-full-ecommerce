package com.sakanlabs.badal.repository;

import com.sakanlabs.badal.entity.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<OrderPayment, UUID> {

    Optional<OrderPayment> findAllByOrder_Id(String orderId);
}
