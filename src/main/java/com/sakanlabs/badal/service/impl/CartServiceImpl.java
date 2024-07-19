package com.sakanlabs.badal.service.impl;


import com.sakanlabs.badal.dto.response.order.CartDto;
import com.sakanlabs.badal.entity.Cart;
import com.sakanlabs.badal.exception.NotFoundException;
import com.sakanlabs.badal.mapper.CartMapper;
import com.sakanlabs.badal.repository.CartRepository;
import com.sakanlabs.badal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto getCartById(String cartId) {
        Cart cart = cartRepository.findById(UUID.fromString(cartId))
                .orElseThrow(() ->
                        new NotFoundException("Cart doesn't exist with the given id: " + cartId));
        return CartMapper.mapToCartDto(cart);
    }

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        Cart savedCart = cartRepository.save(cart);
        return CartMapper.mapToCartDto(savedCart);
    }

    @Override
    public CartDto updateCart(String cartId, CartDto updatedCartDto) {
        Cart cart = cartRepository.findById(UUID.fromString(cartId))
                .orElseThrow(() ->
                        new NotFoundException("Cart doesn't exist with the given id: " + cartId));

        Cart updatedCart = cartMapper.mapToCart(updatedCartDto);

        updatedCart = cartRepository.save(cart);
        return CartMapper.mapToCartDto(updatedCart);
    }

    @Override
    public void deleteCart(String cartId) {
        Cart cart = cartRepository.findById(UUID.fromString(cartId))
                .orElseThrow(() ->
                        new NotFoundException("Cart doesn't exist with the given id: " + cartId));

        cartRepository.deleteById(UUID.fromString(cartId));
    }
}
