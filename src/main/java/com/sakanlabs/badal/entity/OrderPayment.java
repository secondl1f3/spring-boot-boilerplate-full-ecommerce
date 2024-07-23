package com.sakanlabs.badal.entity;

import com.sakanlabs.badal.util.OrderPaymentStatus;
import com.sakanlabs.badal.util.OrderPaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;

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

    private String proofUrl;

    private LocalDateTime confirmedAt;

    private String confirmedBy;

}
