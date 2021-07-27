package com.example.controller.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductListViewController {

    private final IProductService productService;

    @Autowired
    public ProductListViewController(final IProductService service) {
        super();
        productService = checkNotNull(service, "Received a null pointer as service");
    }

    @GetMapping(path = "/list")
    public String showProductList(final ModelMap model) {
        loadViewModel(model);
        return ProductViewConstants.VIEW_ENTITY_LIST;
    }

    private final void loadViewModel(final ModelMap model) {
        model.put(ProductViewConstants.PARAM_ENTITIES, productService.getAllProducts());
    }
}
