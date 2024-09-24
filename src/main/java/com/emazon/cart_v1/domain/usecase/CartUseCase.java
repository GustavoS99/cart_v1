package com.emazon.cart_v1.domain.usecase;

import com.emazon.cart_v1.domain.api.ICartServicePort;
import com.emazon.cart_v1.domain.exception.CartNotFoundException;
import com.emazon.cart_v1.domain.model.Cart;
import com.emazon.cart_v1.domain.spi.IAuthenticationPort;
import com.emazon.cart_v1.domain.spi.ICartPersistencePort;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;
    private final IAuthenticationPort authenticationPort;

    public CartUseCase(
            ICartPersistencePort cartPersistencePort,
            IAuthenticationPort authenticationPort
    ) {
        this.cartPersistencePort = cartPersistencePort;
        this.authenticationPort = authenticationPort;
    }

    @Override
    public void addItem() {
        var userId = authenticationPort.getUserId();
        var cart = cartPersistencePort.findByUserId(userId).orElse(Cart.builder().userId(userId).build());

        cartPersistencePort.save(cart);
    }

    @Override
    public void removeItem() {
        var userId = authenticationPort.getUserId();
        var cart = cartPersistencePort.findByUserId(userId).orElseThrow(CartNotFoundException::new);

        cartPersistencePort.remove(cart.getId());
    }
}
