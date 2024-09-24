package com.emazon.cart_v1.infrastructure.out.jpa.adapter;

import com.emazon.cart_v1.domain.model.Cart;
import com.emazon.cart_v1.domain.spi.ICartPersistencePort;

import java.util.Optional;

public class CartJpaAdapter implements ICartPersistencePort {

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public void save(Cart cart) {

    }

    @Override
    public void remove(Long cartId) {

    }
}
