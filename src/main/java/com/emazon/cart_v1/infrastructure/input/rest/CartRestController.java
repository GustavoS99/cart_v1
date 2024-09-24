package com.emazon.cart_v1.infrastructure.input.rest;

import com.emazon.cart_v1.applicaiton.handler.ICartHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.cart_v1.infrastructure.input.rest.util.PathDefinition.CART;
import static com.emazon.cart_v1.infrastructure.input.rest.util.PathDefinition.ROOT_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(CART)
public class CartRestController {

    private final ICartHandler cartHandler;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item added", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @Operation(summary = "Add item to cart")
    @PostMapping(ROOT_PATH)
    public ResponseEntity<Void> addItem() {
        cartHandler.addItem();
        return ResponseEntity.status(CREATED).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad user request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @Operation(summary = "Remove item from cart")
    @DeleteMapping(ROOT_PATH)
    public ResponseEntity<Void> removeItem() {
        cartHandler.removeItem();
        return ResponseEntity.noContent().build();
    }
}
