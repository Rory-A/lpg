package com.example.model.persistence;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;

import com.example.model.IProduct;


@Entity(name = "Product")
@Table(name = "example_products")
public class Product implements IProduct {


    @Transient
    private static final long serialVersionUID = 1328776989450853491L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id = -1;

    @Column(name = "name", nullable = false, unique = true)
    private String name = "";

    @Column(name = "category", nullable = false)
    private Integer category = null;

    @Column(name = "description")
    private String description = "";

    public Product() {
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

        final Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void setId(final Integer value) {
        id = checkNotNull(value, "Received a null pointer as identifier");
    }

    @Override
    public void setName(final String value) {
        name = checkNotNull(value, "Received a null pointer as name");
    }

    @Override
    public void setCategory(final Integer value) {
        category = checkNotNull(value, "Received a null pointer as category");
    }

    @Override
    public void setDescription(final String value) {
        description = checkNotNull(value, "Received a null pointer as description");
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("entityId", id).toString();
    }

}
