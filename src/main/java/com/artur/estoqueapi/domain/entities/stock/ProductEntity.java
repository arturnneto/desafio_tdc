package com.artur.estoqueapi.domain.entities.stock;

import com.artur.estoqueapi.domain.entities.auth.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantityAtStock;

    private Long categoryId;

    @CreationTimestamp
    private Instant creationTimestamp;
}
