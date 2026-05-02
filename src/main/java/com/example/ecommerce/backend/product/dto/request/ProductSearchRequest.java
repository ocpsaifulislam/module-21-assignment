package com.example.ecommerce.backend.product.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ProductSearchRequest(
        @Size(max = 200, message = "Product name cannot exceed 200 characters")
        String name,

        @Size(max = 100, message = "Product SKU cannot exceed 100 characters")
        String sku,

        @Min(value = 1, message = "Category ID must be positive")
        Long categoryId,

        @DecimalMin(value = "0.0", inclusive = true, message = "Minimum price cannot be negative")
        Double minPrice,

        @DecimalMin(value = "0.0", inclusive = true, message = "Maximum price cannot be negative")
        Double maxPrice,

        @Min(value = 0, message = "Page index cannot be negative")
        Integer page,

        @Min(value = 1, message = "Page size must be at least 1")
        Integer size
) {
}
