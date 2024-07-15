package org.ismetg.repository;

import org.ismetg.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    Optional<Category> findBynameIgnoreCase(String name);
}
