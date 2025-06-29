package com.ecom.productcatalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
}
