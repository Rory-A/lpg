package com.example.model.form;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

public final class ProductForm implements Serializable {

    private static final long serialVersionUID = 1328776989450853491L;

    @NotEmpty
    private String name;

    private String description;

    private Integer category;

    public ProductForm() {
        super();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final ProductForm other = (ProductForm) obj;
        return Objects.equals(name, other.name);
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final Integer getCategory() {
        return category;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name);
    }

    public final void setName(final String value) {
        name = checkNotNull(value, "Received a null pointer as name");
    }

    public final void setDescription(final String value) {
        description = checkNotNull(value, "Received a null pointer as description");
    }

    public final void setCategory(final Integer value) {
        category = checkNotNull(value, "Received a null pointer as category");
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }
}
