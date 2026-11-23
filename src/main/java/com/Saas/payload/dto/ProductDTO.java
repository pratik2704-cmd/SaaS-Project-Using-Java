
package com.Saas.payload.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String sku;

    private String description;

    private Double mrp;

    private Double sellingPrice;

    private String brand;

    private String image;

    private CategoryDTO category;

    private Long categoryId;

    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
