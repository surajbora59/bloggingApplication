package com.app.blog.repository;

import java.util.List;

import com.app.blog.entity.Category;
import com.app.blog.entity.Posts;
import com.app.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {
    List<Posts> findAllByUserId(User user);
    List<Posts> findAllByCategory(Category category);
    List<Posts> findAllByUserIdAndCategory(User user, Category category);
    List<Posts> findAllByTitleContains(String title);
}
