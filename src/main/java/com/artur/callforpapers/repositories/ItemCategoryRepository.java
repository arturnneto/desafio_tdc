package com.artur.callforpapers.repositories;

import com.artur.callforpapers.domain.entities.stock.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
    Optional<ItemCategoryEntity> findByCategoryName(String categoryName);
}
