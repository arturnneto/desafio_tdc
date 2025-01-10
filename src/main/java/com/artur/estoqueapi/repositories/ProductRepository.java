package com.artur.estoqueapi.repositories;

import com.artur.estoqueapi.domain.entities.stock.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
