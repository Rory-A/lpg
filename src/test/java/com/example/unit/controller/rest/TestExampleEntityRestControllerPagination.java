package com.example.unit.controller.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controller.entity.ProductRestController;
import com.example.model.IProduct;
import com.example.service.IProductService;
import com.example.config.UrlConfig;

public final class TestExampleEntityRestControllerPagination {

    private ArgumentCaptor<Pageable> captor;

    private MockMvc mockMvc;

    private final IProductService service;

    public TestExampleEntityRestControllerPagination() {
        super();

        service = getProductService();
    }

    @BeforeEach
    public final void setUpMockContext() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductRestController(service))
                .setCustomArgumentResolvers(
                        new PageableHandlerMethodArgumentResolver())
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                .alwaysExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .build();
    }

    @Test
    public final void testGet_Page_SetInPagination() throws Exception {
        final Pageable pageable;

        mockMvc.perform(getGetRequestWithPage());

        pageable = captor.getValue();

        Assertions.assertEquals(10, pageable.getPageNumber());
    }

    @Test
    public final void testGet_WithoutPagination_DefaultValues()
            throws Exception {
        final Pageable pageable;

        mockMvc.perform(getGetRequest());

        pageable = captor.getValue();

        Assertions.assertEquals(20, pageable.getPageSize());
        Assertions.assertEquals(0, pageable.getPageNumber());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private final IProductService getProductService() {
        final IProductService service;   // Mocked service
        final Collection<IProduct> products;

        service = Mockito.mock(IProductService.class);

        products = new ArrayList<>();
        products.add(Mockito.mock(IProduct.class));
        products.add(Mockito.mock(IProduct.class));
        products.add(Mockito.mock(IProduct.class));

        captor = ArgumentCaptor.forClass(Pageable.class);

        Mockito.when(service.getProducts(captor.capture())).thenReturn((Iterable) products);

        return service;
    }

    private final RequestBuilder getGetRequest() {
        return MockMvcRequestBuilders.get(UrlConfig.URL_REST)
                .contentType(MediaType.APPLICATION_JSON);
    }

    private final RequestBuilder getGetRequestWithPage() {
        return MockMvcRequestBuilders.get(UrlConfig.URL_REST + "?page=10")
                .contentType(MediaType.APPLICATION_JSON);
    }
}
