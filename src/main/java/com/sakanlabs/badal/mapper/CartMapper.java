package com.sakanlabs.badal.mapper;

import com.sakanlabs.badal.dto.response.order.CartDto;
import com.sakanlabs.badal.entity.Cart;
import com.sakanlabs.badal.entity.Product;
import com.sakanlabs.badal.entity.User;
import com.sakanlabs.badal.repository.ProductRepository;
import com.sakanlabs.badal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();
        User user = userRepository.getReferenceById(UUID.fromString(cartDto.userId));
        Product product = productRepository.getReferenceById(UUID.fromString(cartDto.getProductId()));
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(cartDto.quantity);
        cart.setTotalProductPrice(cartDto.totalProductPrice);
        return cart;
    }

    public static CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId().toString());
        cartDto.setUserId(cart.getUser().getId().toString());
        cartDto.setProductId(cart.getProduct().getId().toString());
        cartDto.setProductName(cart.getProduct().getName());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setTotalProductPrice(cart.getTotalProductPrice());
        return cartDto;
    }

}
