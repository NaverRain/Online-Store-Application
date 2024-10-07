package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.CategoryDto;

public class CategoryDtoToCategoryConverter {

    public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        return categoryDto;
    }
}
