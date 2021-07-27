package com.example.unit.controller.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.entity.ProductRestController;
import com.example.model.IProduct;
import com.example.service.IProductService;
import com.example.config.UrlConfig;

public final class TestExampleEntityRestController {

    private MockMvc mockMvc;

    public TestExampleEntityRestController() {
        super();
    }

    @BeforeEach
    public final void setUpMockContext() {
        mockMvc = MockMvcBuilders.standaloneSetup(getController())
                .setCustomArgumentResolvers(
                        new PageableHandlerMethodArgumentResolver())
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                .alwaysExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .build();
    }

    @Test
    public final void testGet_ExpectedResults() throws Exception {
        final ResultActions result; // Request result

        result = mockMvc.perform(getGetRequest());

        // The operation was accepted
        result.andExpect(MockMvcResultMatchers.status().isOk());

        // The response model contains the expected attributes
        result.andExpect(
                MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private final ProductRestController getController() {
        final IProductService service;   // Mocked service
        final Collection<IProduct> products; // Returned entities

        service = Mockito.mock(IProductService.class);

        products = new ArrayList<>();
        products.add(Mockito.mock(IProduct.class));
        products.add(Mockito.mock(IProduct.class));
        products.add(Mockito.mock(IProduct.class));

        Mockito.when(service.getProducts(ArgumentMatchers.any()))
                .thenReturn((Iterable) products);

        return new ProductRestController(service);
    }

    private final RequestBuilder getGetRequest() {
        return MockMvcRequestBuilders.get(UrlConfig.URL_REST)
                .contentType(MediaType.APPLICATION_JSON);
    }

}
