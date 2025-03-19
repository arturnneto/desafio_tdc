package com.artur.callforpapers.controllers.stock;

import com.artur.callforpapers.domain.dto.stock.ItemCategoryDto;
import com.artur.callforpapers.domain.entities.stock.ItemCategoryEntity;
import com.artur.callforpapers.mappers.Mapper;
import com.artur.callforpapers.service.stock.ItemCategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoryController {


    @Autowired
    private ItemCategoryService itemCategoryService;
    @Autowired
    private Mapper<ItemCategoryEntity, ItemCategoryDto> itemCategoryMapper;

    @Transactional
    @PostMapping("/category")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<ItemCategoryDto> createNewCategory(@RequestBody ItemCategoryDto itemCategoryDto) {
        ItemCategoryEntity categoryFromDatabase = itemCategoryMapper.mapFrom(itemCategoryDto);
        ItemCategoryEntity newCategory = itemCategoryService.createNewCategory(categoryFromDatabase);
        itemCategoryService.saveNewCategory(newCategory);

        return new ResponseEntity<>(itemCategoryMapper.mapTo(newCategory), HttpStatus.CREATED);
    }
}
