package com.sakanlabs.badal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Builder
public class Product extends AbstractBaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int quantity;

    @Column
    private BigDecimal price;
}
