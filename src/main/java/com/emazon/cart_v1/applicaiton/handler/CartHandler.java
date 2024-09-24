package com.emazon.cart_v1.applicaiton.handler;

import com.emazon.cart_v1.domain.api.ICartServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartHandler implements ICartHandler {

    private final ICartServicePort cartServicePort;

    @Override
    public void addItem() {
        cartServicePort.addItem();
    }

    @Override
    public void removeItem() {
        cartServicePort.removeItem();
    }
}
