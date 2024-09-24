package com.emazon.cart_v1.infrastructure.input.rest;

import com.emazon.cart_v1.domain.model.Cart;
import com.emazon.cart_v1.domain.spi.ICartPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static com.emazon.cart_v1.infrastructure.input.rest.util.PathDefinition.CART;
import static com.emazon.cart_v1.infrastructure.input.rest.util.PathDefinition.ROOT_PATH;
import static com.emazon.cart_v1.util.GlobalConstants.CUSTOMER;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartRestControllerTest {

    private static final String WORKER_USERNAME = "2";
    private static final String WAREHOUSE_WORKER = "WAREHOUSE_WORKER";
    private static final String CUSTOMER_USERNAME = "3";

    @MockBean
    private ICartPersistencePort cartPersistencePort;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = CUSTOMER_USERNAME, roles = CUSTOMER)
    void when_addItemWithCustomerRole_expect_ok() throws Exception {
        mockMvc.perform(post(CART.concat(ROOT_PATH)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = WORKER_USERNAME, roles = WAREHOUSE_WORKER)
    void expect_forbidden_when_addItemWithARoleDistinctToCustomer() throws Exception {
        mockMvc.perform(post(CART.concat(ROOT_PATH)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAnonymousUser
    void expect_unauthorized_when_addItemWithoutACustomerRole() throws Exception {
        mockMvc.perform(post(CART.concat(ROOT_PATH)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = CUSTOMER_USERNAME, roles = CUSTOMER)
    void when_removeItemWithCustomerRole_expect_noContent() throws Exception {
        Cart cart = Cart.builder()
                .id(1L)
                .userId(Long.parseLong(CUSTOMER_USERNAME))
                .createdAt(Instant.now().minus(Duration.ofDays(1)))
                .build();

        when(cartPersistencePort.findByUserId(anyLong())).thenReturn(Optional.of(cart));

        mockMvc.perform(delete(CART.concat(ROOT_PATH)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = CUSTOMER_USERNAME, roles = CUSTOMER)
    void expect_CartNotFoundException_when_removeItemWithCustomerRole() throws Exception {

        mockMvc.perform(delete(CART.concat(ROOT_PATH)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = WORKER_USERNAME, roles = WAREHOUSE_WORKER)
    void expect_forbidden_when_removeItemWithARoleDistinctToCustomer() throws Exception {

        mockMvc.perform(delete(CART.concat(ROOT_PATH)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAnonymousUser
    void expect_unauthorized_when_removeItemWithoutACustomerRole() throws Exception {

        mockMvc.perform(delete(CART.concat(ROOT_PATH)))
                .andExpect(status().isUnauthorized());
    }
}