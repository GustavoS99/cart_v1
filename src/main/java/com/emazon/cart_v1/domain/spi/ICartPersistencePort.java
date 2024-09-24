package com.emazon.cart_v1.domain.spi;

import com.emazon.cart_v1.domain.model.Cart;

import java.util.Optional;

public interface ICartPersistencePort {

    Optional<Cart> findByUserId(Long userId);

    void save(Cart cart);

    void remove(Long cartId);
}
