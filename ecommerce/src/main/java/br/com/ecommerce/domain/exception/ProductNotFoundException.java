package br.com.ecommerce.domain.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final String id;

    public ProductNotFoundException(String id) {
        super(String.format("Product with id %s not found", id));
        this.id = id;
    }
}
