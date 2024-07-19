package com.sakanlabs.badal.service;

import com.sakanlabs.badal.dto.response.order.CartDto;

public interface CartService {

    CartDto getCartById(String cartId);
    CartDto createCart(CartDto cartDto);
    CartDto updateCart(String cartId, CartDto updatedCartDto);
    void deleteCart(String cartId);
}
