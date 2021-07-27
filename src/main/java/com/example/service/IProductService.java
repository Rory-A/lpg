package com.example.service;

import org.springframework.data.domain.Pageable;

import com.example.model.persistence.Product;
import com.example.model.IProduct;

public interface IProductService {

    public IProduct add(final Product product);

    public IProduct findById(final Integer identifier);

    public Iterable<Product> getAllProducts();

    public Iterable<Product> getProducts(final Pageable page);

    public void remove(final Product product);

}
