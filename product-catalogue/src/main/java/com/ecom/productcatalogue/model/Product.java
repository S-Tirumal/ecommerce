package com.ecom.productcatalogue.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel{
    private String name;
    private Double price;
    private String description;
    private String imageUrl;

    @ManyToOne
    private Category category;
}
