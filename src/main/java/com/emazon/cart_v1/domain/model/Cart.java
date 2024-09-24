package com.emazon.cart_v1.domain.model;

import com.emazon.cart_v1.domain.model.builder.IBuilder;

import java.time.Instant;

public class Cart {

    private Long id;

    private Long userId;

    private Instant createdAt;

    private Instant updatedAt;

    private Boolean payed;

    private Boolean cancelled;

    public static CartBuilder builder() {
        return new CartBuilder();
    }

    public static class CartBuilder implements IBuilder<Cart> {

        private Long id;

        private Long userId;

        private Instant createdAt;

        private Instant updatedAt;

        private Boolean payed;

        private Boolean cancelled;

        public CartBuilder() {
            createdAt = Instant.now();

            payed = false;

            cancelled = false;
        }

        public CartBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CartBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public CartBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CartBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CartBuilder payed(Boolean payed) {
            this.payed = payed;
            return this;
        }

        public CartBuilder cancelled(Boolean cancelled) {
            this.cancelled = cancelled;
            return this;
        }

        @Override
        public Cart build() {
            Cart cart = new Cart();

            cart.setId(id);

            cart.setUserId(userId);

            cart.setCreatedAt(createdAt);

            cart.setUpdatedAt(updatedAt);

            cart.setPayed(payed);

            cart.setCancelled(cancelled);

            return cart;
        }
    }

    public Cart() {
        createdAt = Instant.now();

        payed = false;

        cancelled = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
