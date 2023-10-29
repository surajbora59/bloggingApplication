package com.app.blog.service;

import java.util.List;

import com.app.blog.dto.CategoryDto;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getCategoryByTitle(String categoryTitle);
    void deleteCategoryById(Integer categoryId);

    List<CategoryDto> getAllCategories();
}
