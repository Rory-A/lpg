package com.example.integration.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import com.example.Application;
import com.example.model.IProduct;
import com.example.model.persistence.Product;
import com.example.service.IProductService;

@SpringJUnitConfig
@Transactional
@Rollback
@SpringBootTest(classes = Application.class)
public class ITProductService {

    @Autowired
    private IProductService service;

    public ITProductService() {
        super();
    }

    @Test
    public void testAdd_NotExisting_Added() {
        final Product product;
        final Integer productsCount;
        final Integer finalProductsCount;

        productsCount = ((Collection<Product>) service.getAllProducts()).size();

        product = new Product();
        product.setName("ABC");
        product.setCategory(3);
        product.setDescription("some description");

        service.add(product);

        finalProductsCount = ((Collection<Product>) service.getAllProducts()).size();

        Assertions.assertEquals(finalProductsCount, new Integer(productsCount + 1));
    }

    @Test
    public void testFindById_Existing_Valid() {
        final IProduct product; // Found entity

        product = service.findById(1);

        Assertions.assertEquals(product.getId(), new Integer(1));
    }

    @Test
    public void testFindById_NotExisting_Invalid() {
        final IProduct product; // Found entity

        product = service.findById(100);

        Assertions.assertEquals(product.getId(), new Integer(-1));
    }

}
