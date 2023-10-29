package com.app.blog.controllers;

import java.util.List;

import com.app.blog.dto.CategoryDto;
import com.app.blog.response.GenericResponse;
import com.app.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/api/category", produces = "application/json", consumes = "application/json")
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }
    @GetMapping(value = "/api/category", produces = "application/json")
    private ResponseEntity<List<CategoryDto>> getCategoryUsingTitle(@RequestParam(name = "categoryTitle", required = false) String categoryTitle){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryByTitle(categoryTitle));
    }

    @GetMapping(value = "/api/categories",produces = "application/json")
    private ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @GetMapping(value = "/api/category/{categoryId}", produces = "application/json")
    private ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
    }

    @DeleteMapping(value = "/api/category/{categoryId}", produces = "application/json")
    private ResponseEntity<GenericResponse> deleteCategoryById(@PathVariable Integer categoryId){
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().meta("Category deleted").build());
    }

    @PutMapping(value = "/api/category/{categoryId}", produces = "application/json", consumes = "application/json")
    private ResponseEntity<CategoryDto> updateCategoryById(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryDto,categoryId));
    }
}
