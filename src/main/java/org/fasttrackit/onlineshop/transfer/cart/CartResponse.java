package org.fasttrackit.onlineshop.transfer.cart;

import org.fasttrackit.onlineshop.transfer.product.ProductInCartResponse;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {

    private  long id;
    private Set<ProductInCartResponse> products = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long is) {
        this.id = is;
    }

    public Set<ProductInCartResponse> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInCartResponse> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "is=" + id +
                ", products=" + products +
                '}';
    }
}
