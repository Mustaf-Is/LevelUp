package com.example.levelup.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.levelup.models.Category;
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
