package com.example.unit.controller.form;

import java.util.Collection;
import java.util.ArrayList;

import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.controller.entity.ProductFormController;
import com.example.controller.entity.ProductViewConstants;
import com.example.model.persistence.Product;
import com.example.service.IProductService;
import com.example.config.UrlConfig;

public final class TestProductFormControllerShowForm {

    private MockMvc mockMvc;

    public TestProductFormControllerShowForm() {
        super();
    }

    @BeforeEach
    public final void setUpMockContext() {
        mockMvc = MockMvcBuilders.standaloneSetup(getController())
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
    }

    @Test
    public final void testShowForm_ExpectedAttributeModel() throws Exception {
        final ResultActions result; // Request result

        result = mockMvc.perform(getViewRequest());

        // The response model contains the expected attributes
        result.andExpect(MockMvcResultMatchers.model()
                .attributeExists(ProductViewConstants.BEAN_FORM));
    }

    private final ProductFormController getController() {
        final IProductService service;
        final Collection<Product> products;

        service = Mockito.mock(IProductService.class);

        products = new ArrayList<>();

        Mockito.when(service.getAllProducts()).thenReturn(products);

        return new ProductFormController(service);
    }

    private final RequestBuilder getViewRequest() {
        return MockMvcRequestBuilders.get(UrlConfig.URL_FORM);
    }
}
