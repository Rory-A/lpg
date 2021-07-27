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

public final class TestProductFormControllerServiceCalled {

    private MockMvc mockMvc;

    private IProductService service;

    public TestProductFormControllerServiceCalled() {
        super();
    }

    @BeforeEach
    public final void setUpMockContext() {
        service = Mockito.mock(IProductService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(getController(service))
                .alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
    }

    @Test
    public final void testSendFormData_CalledService() throws Exception {
        final ResultActions result; // Request result

        result = mockMvc.perform(getFormRequest());

        // The view is valid
        result.andExpect(MockMvcResultMatchers.view().name(ProductViewConstants.VIEW_ENTITY_LIST));
        
        Mockito.verify(service, Mockito.times(1)).add(Mockito.any());
    }

    private final ProductFormController getController(final IProductService service) {
        final Collection<Product> products;

        products = new ArrayList<>();

        Mockito.when(service.getAllProducts()).thenReturn(products);

        return new ProductFormController(service);
    }

    private final RequestBuilder getFormRequest() {
        return MockMvcRequestBuilders.post(UrlConfig.URL_FORM_POST)
                                        .param("name", "name")
                                        .param("category", "1")
                                        .param("description", "a short description");
    }
}
