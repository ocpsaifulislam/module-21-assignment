package com.example.ecommerce.backend.product.repository;

import com.example.ecommerce.backend.product.entity.Category;
import com.example.ecommerce.backend.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Repository for product catalog persistence operations.
 *
 * @author Pial Kanti Samadder
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Checks whether a product already exists for the supplied SKU.
     *
     * @param sku product stock keeping unit
     * @return {@code true} when the SKU is already used
     */
    boolean existsBySku(String sku);

    @Query("""
            SELECT p FROM Product p
            WHERE p.isActive = true
            AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:sku IS NULL OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :sku, '%')))
            AND (:categoryId IS NULL OR p.category.id = :categoryId)
            AND (:minPrice IS NULL OR p.price >= :minPrice)
            AND (:maxPrice IS NULL OR p.price <= :maxPrice)
            """)
    Page<Product> searchActiveProducts(
            @Param("name") String name,
            @Param("sku") String sku,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );

}
