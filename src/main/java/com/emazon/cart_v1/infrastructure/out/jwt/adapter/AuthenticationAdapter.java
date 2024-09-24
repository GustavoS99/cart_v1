package com.emazon.cart_v1.infrastructure.out.jwt.adapter;

import com.emazon.cart_v1.domain.spi.IAuthenticationPort;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationAdapter implements IAuthenticationPort {

    @Override
    public Long getUserId() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
