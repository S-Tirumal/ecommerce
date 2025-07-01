package com.ecom.productcatalogue.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class FakeStoreProductDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
}
