package com.emazon.cart_v1.infrastructure.out.jwt.config;

import com.emazon.cart_v1.infrastructure.out.jwt.config.filter.JwtTokenValidator;
import com.emazon.cart_v1.infrastructure.out.jwt.entry.point.AuthenticationEntryPointImpl;
import com.emazon.cart_v1.infrastructure.out.jwt.handler.AccessDeniedHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.emazon.cart_v1.infrastructure.input.rest.util.PathDefinition.*;
import static com.emazon.cart_v1.util.GlobalConstants.CUSTOMER;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenValidator jwtTokenValidator;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {

                    authorizeRequests.requestMatchers(HttpMethod.POST, CART.concat(ROOT_PATH))
                            .hasRole(CUSTOMER);

                    authorizeRequests.requestMatchers(HttpMethod.DELETE, CART.concat(ROOT_PATH))
                            .hasRole(CUSTOMER);

                    authorizeRequests.requestMatchers(SWAGGER_UI).permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.GET, OPEN_API).permitAll();

                    authorizeRequests.anyRequest().denyAll();
                })
                .addFilterBefore(jwtTokenValidator, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(customizer -> {
                    customizer.accessDeniedHandler(accessDeniedHandler);
                    customizer.authenticationEntryPoint(authenticationEntryPoint);
                })
                .build();
    }
}
