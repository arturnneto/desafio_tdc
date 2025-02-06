package com.artur.estoqueapi.service.stock;

import com.artur.estoqueapi.domain.entities.stock.ItemCategoryEntity;

import java.util.Optional;

public interface ItemCategoryService {

    void checkIfCategoryExists(ItemCategoryEntity itemCategoryEntity);

    ItemCategoryEntity createNewCategory(ItemCategoryEntity itemCategoryEntity);

    ItemCategoryEntity saveNewCategory(ItemCategoryEntity itemCategoryEntity);

    Optional<ItemCategoryEntity> getCategory(ItemCategoryEntity itemCategoryEntity);
}
