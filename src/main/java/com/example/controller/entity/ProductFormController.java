package com.example.controller.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.IProductService;
import com.example.model.persistence.Product;
import com.example.model.form.ProductForm;

@Controller
@RequestMapping("/product")
public class ProductFormController {

    private final IProductService productService;

    @Autowired
    public ProductFormController(final IProductService service) {
        super();

        productService = checkNotNull(service, "Received a null pointer as service");
    }

    @ModelAttribute(ProductViewConstants.BEAN_FORM)
    public ProductForm getProductForm() {
        return new ProductForm();
    }

    @PostMapping
    public String saveProduct(final ModelMap model,
            @ModelAttribute(ProductViewConstants.BEAN_FORM) @Valid final ProductForm form,
            final BindingResult bindingResult, final HttpServletResponse response) {
        final String path;
        final Product product;

        if (bindingResult.hasErrors()) {
            path = ProductViewConstants.VIEW_ENTITY_FORM;
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            product = new Product();
            product.setName(form.getName());
            product.setCategory(form.getCategory());
            product.setDescription(form.getDescription());

            productService.add(product);

            // TODO: This flow decision shouldn't be handled by the controller
            // TODO: This should be a redirection to the list controller
            // Loads required data into the model
            loadViewModel(model);

            path = ProductViewConstants.VIEW_ENTITY_LIST;
        }

        return path;
    }

    @GetMapping(path = "/edit")
    public String showProductForm() {
        return ProductViewConstants.VIEW_ENTITY_FORM;
    }

    private final void loadViewModel(final ModelMap model) {
        model.put(ProductViewConstants.PARAM_ENTITIES, productService.getAllProducts());
    }

}
