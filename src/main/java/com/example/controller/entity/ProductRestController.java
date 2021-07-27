package com.example.controller.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.IProduct;
import com.example.service.IProductService;

@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

    private final IProductService productService;

    @Autowired
    public ProductRestController(final IProductService service) {
        super();

        productService = checkNotNull(service, "Received a null pointer as service");
    }

    @GetMapping
    public Iterable<? extends IProduct> getProducts(final Pageable page) {
        return productService.getProducts(page);
    }

}
