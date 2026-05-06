package com.example.ecommerce.backend.product.repository;

import com.example.ecommerce.backend.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);

    Page<Category> findByIsActiveTrue(Pageable pageable);

    @Query("""
            SELECT c FROM Category c
            WHERE c.isActive = true
            AND (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:code IS NULL OR LOWER(c.code) LIKE LOWER(CONCAT('%', :code, '%')))
            """)
    Page<Category> searchCategories(
            @Param("name") String name,
            @Param("code") String code,
            Pageable pageable
    );
}
