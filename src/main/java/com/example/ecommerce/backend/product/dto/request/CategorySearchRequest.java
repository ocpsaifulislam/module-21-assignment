package com.example.ecommerce.backend.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record CategorySearchRequest(
        @Size(max = 120, message = "Category name cannot exceed 120 characters")
        String name,

        @Size(max = 50, message = "Category code cannot exceed 50 characters")
        String code,

        @Min(value = 0, message = "Page index cannot be negative")
        Integer page,

        @Min(value = 1, message = "Page size must be at least 1")
        Integer size
) {
}
