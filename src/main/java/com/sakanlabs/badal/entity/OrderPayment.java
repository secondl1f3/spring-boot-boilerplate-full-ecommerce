package com.sakanlabs.badal.entity;

import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderPaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_payments")
public class OrderPayment extends AbstractBaseEntity {

    private OrderPaymentType type;

    private OrderPaymentStatus status;

}
