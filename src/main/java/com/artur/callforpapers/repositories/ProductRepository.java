package com.artur.callforpapers.repositories;

import com.artur.callforpapers.domain.entities.stock.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
