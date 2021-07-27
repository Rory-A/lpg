package com.example.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalBindingInitializer {

    public GlobalBindingInitializer() {
        super();
    }

    @InitBinder
    public void setDisallowedFields(final WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
