package com.app.blog.repository;

import java.util.List;

import com.app.blog.dto.CategoryDto;
import com.app.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT new com.app.blog.dto.CategoryDto(c.categoryId as categoryId, c.categoryTitle as categoryTitle, c.categoryDescription as categoryDescription) FROM Category c WHERE c.categoryTitle like %:categoryTitle%")
    List<CategoryDto> findByCategoryTitle(@Param("categoryTitle") String categoryTitle);
}
