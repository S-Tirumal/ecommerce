package com.ecom.productcatalogue.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category  extends BaseModel{

    private String title;

    private String description;

    @OneToMany
    @JsonBackReference
    private List<Product> productList;

}
