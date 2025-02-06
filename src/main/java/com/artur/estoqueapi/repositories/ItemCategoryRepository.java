package com.artur.estoqueapi.repositories;

import com.artur.estoqueapi.domain.entities.stock.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
    Optional<ItemCategoryEntity> findByCategoryName(String categoryName);
}
