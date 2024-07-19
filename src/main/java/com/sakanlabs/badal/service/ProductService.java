package com.sakanlabs.badal.service;

import com.sakanlabs.badal.dto.response.order.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(String productId);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(String productId, ProductDto updatedProductDto);
    void deleteProduct(String productId);

}
