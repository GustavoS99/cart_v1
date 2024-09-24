package com.emazon.cart_v1.infrastructure.exception.handler;

import com.emazon.cart_v1.domain.exception.CartNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

import static com.emazon.cart_v1.infrastructure.exception.response.ExceptionResponse.CART_NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFoundException(
            CartNotFoundException cartNotFoundException
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, CART_NOT_FOUND.getMessage()));
    }
}
