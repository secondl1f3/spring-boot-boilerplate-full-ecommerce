package com.sakanlabs.badal.service.impl;

import com.sakanlabs.badal.dto.response.order.ProductDto;
import com.sakanlabs.badal.entity.Product;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.mapper.ProductEntityMapper;
import com.sakanlabs.badal.repository.ProductRepository;
import com.sakanlabs.badal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductEntityMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductEntityMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(String productId) {
        Product product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() ->
                        new NotFoundException("Product doesn't exist with the given id: " + productId));
        return ProductEntityMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) ->
                        ProductEntityMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updatedProductDto) {
        Product product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() ->
                        new NotFoundException("Product doesn't exist with the given id: " + productId));

        product.setName(updatedProductDto.getProductName());
        product.setDescription(updatedProductDto.getDescription());
        product.setQuantity(updatedProductDto.getQuantity());
        product.setPrice(updatedProductDto.getPrice());

        Product updatedProduct = productRepository.save(product);
        return ProductEntityMapper.mapToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() ->
                        new NotFoundException("Product doesn't exist with the given id: " + productId));

        productRepository.deleteById(UUID.fromString(productId));
    }
}
