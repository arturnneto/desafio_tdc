package com.artur.estoqueapi.domain.dto.stock;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ItemCategoryDto {
    private String categoryName;
}
