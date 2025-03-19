package com.artur.callforpapers.service.stock.impl;

import com.artur.callforpapers.domain.entities.stock.ItemCategoryEntity;
import com.artur.callforpapers.repositories.ItemCategoryRepository;
import com.artur.callforpapers.service.stock.ItemCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public Optional<ItemCategoryEntity> getCategory(ItemCategoryEntity itemCategoryEntity) {
        return itemCategoryRepository.findByCategoryName(itemCategoryEntity.getCategoryName());
    }

    @Override
    public ItemCategoryEntity saveNewCategory(ItemCategoryEntity itemCategoryEntity) {
        return itemCategoryRepository.save(itemCategoryEntity);
    }

    @Override
    public void checkIfCategoryExists(ItemCategoryEntity itemCategoryEntity) {
        Optional<ItemCategoryEntity> categoryFromDatabase = getCategory(itemCategoryEntity);

        if (categoryFromDatabase.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Category" + " " + itemCategoryEntity.getCategoryName() + "Already exists on database.");
        }
    }

    @Override
    public ItemCategoryEntity createNewCategory(ItemCategoryEntity itemCategoryEntity) {
        checkIfCategoryExists(itemCategoryEntity);

        ItemCategoryEntity newCategory = new ItemCategoryEntity();
        newCategory.setCategoryName(itemCategoryEntity.getCategoryName());

        return newCategory;
    }


}
