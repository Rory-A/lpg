package com.example.service;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.persistence.Product;
import com.example.model.IProduct;
import com.example.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository repository) {
        super();
        productRepository = checkNotNull(repository, "Received a null pointer as repository");
    }

    @Override
    public final IProduct add(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public final IProduct findById(final Integer identifier) {
        final IProduct product;

        checkNotNull(identifier, "Received a null pointer as identifier");

        if (productRepository.existsById(identifier)) {
            product = productRepository.getOne(identifier);
        } else {
            product = new Product();
        }

        return product;
    }

    @Override
    public final Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public final Iterable<Product>
            getProducts(final Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public final void remove(final Product product) {
        productRepository.delete(product);
    }

}
