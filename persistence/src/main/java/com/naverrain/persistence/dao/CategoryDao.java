package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.CategoryDto;

public interface CategoryDao {

    CategoryDto getCategoryByCategoryId(int id);
}
