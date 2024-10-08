package com.sakanlabs.badal.mapper;

import com.sakanlabs.badal.dto.response.order.ProductDto;
import com.sakanlabs.badal.entity.Product;

public class ProductEntityMapper {

    public static Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getQuantity(),
                productDto.getPrice()
        );
    }

    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

}
