package com.metal.reshetka.repository;

import com.metal.reshetka.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
