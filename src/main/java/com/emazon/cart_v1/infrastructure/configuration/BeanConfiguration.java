package com.emazon.cart_v1.infrastructure.configuration;

import com.emazon.cart_v1.domain.api.ICartServicePort;
import com.emazon.cart_v1.domain.spi.IAuthenticationPort;
import com.emazon.cart_v1.domain.spi.ICartPersistencePort;
import com.emazon.cart_v1.domain.usecase.CartUseCase;
import com.emazon.cart_v1.infrastructure.out.jpa.adapter.CartJpaAdapter;
import com.emazon.cart_v1.infrastructure.out.jwt.adapter.AuthenticationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ICartPersistencePort cartPersistencePort() {
        return new CartJpaAdapter();
    }

    @Bean
    public IAuthenticationPort authenticationPort() {
        return new AuthenticationAdapter();
    }
    @Bean
    public ICartServicePort cartServicePort(
            ICartPersistencePort cartPersistencePort,
            IAuthenticationPort authenticationPort
    ) {
        return new CartUseCase(cartPersistencePort, authenticationPort);
    }
}
