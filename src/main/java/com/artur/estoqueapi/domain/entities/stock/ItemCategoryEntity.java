package com.artur.estoqueapi.domain.entities.stock;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_categories")
public class ItemCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @Column(unique = true)
    private String categoryName;

    private Long numberOfProductsInStock = 0L;

    private Long numberOfProductsSold = 0L;

    @CreationTimestamp
    private Instant creationTimestamp;
}
