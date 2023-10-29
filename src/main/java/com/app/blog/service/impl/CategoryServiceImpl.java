package com.app.blog.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import com.app.blog.dto.CategoryDto;
import com.app.blog.entity.Category;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.repository.CategoryRepository;
import com.app.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = modelMapper.map(categoryDto,Category.class);
        categoryRepository.save(newCategory);
        return modelMapper.map(newCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id", (long) id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        categoryRepository.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id", (long) categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategoryByTitle(String categoryTitle) {
        return categoryRepository.findByCategoryTitle(categoryTitle);
    }


    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category deletedCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        categoryRepository.delete(deletedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        Type listType = new TypeToken<List<CategoryDto>>() {}.getType();
        return modelMapper.map(categories, listType);
    }
}
