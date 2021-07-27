package com.example.unit.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.form.ProductForm;

public final class TestProductFormValidation {

    private Validator validator;

    public TestProductFormValidation() {
        super();
    }

    @BeforeEach
    public final void setUpValidator() {
        validator = createValidator();
    }

    @Test
    public final void testValidation_EmptyName_Error() {
        final ProductForm form; // Tested form
        final Set<ConstraintViolation<ProductForm>> errors;
        final ConstraintViolation<ProductForm> error;

        form = new ProductForm();

        form.setName("");

        errors = validator.validate(form);

        Assertions.assertEquals(1, errors.size());

        error = errors.iterator().next();
        Assertions.assertEquals("name", error.getPropertyPath().toString());
    }

    @Test
    public final void testValidation_NullName_Error() {
        final ProductForm form; // Tested form
        final Set<ConstraintViolation<ProductForm>> errors;
        final ConstraintViolation<ProductForm> error;

        form = new ProductForm();

        errors = validator.validate(form);

        Assertions.assertEquals(1, errors.size());

        error = errors.iterator().next();
        Assertions.assertEquals("name", error.getPropertyPath().toString());
    }

    private final Validator createValidator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean;

        localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

}
