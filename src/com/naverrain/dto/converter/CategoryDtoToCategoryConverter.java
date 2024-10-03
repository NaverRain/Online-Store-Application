package com.naverrain.dto.converter;

import com.naverrain.dto.CategoryDto;

public class CategoryDtoToCategoryConverter {

    public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(categoryName);
        return categoryDto;
    }
}
