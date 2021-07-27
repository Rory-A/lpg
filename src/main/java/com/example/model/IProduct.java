package com.example.model;

import java.io.Serializable;

public interface IProduct extends Serializable {

    public Integer getId();

    public String getName();

    public Integer getCategory();

    public String getDescription();

    public void setId(final Integer identifier);

    public void setName(final String name);

    public void setCategory(final Integer category);

    public void setDescription(final String description);
}
