package com.ecom.productcatalogue.repository;

import com.ecom.productcatalogue.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    void deleteById(Long aLong);
}
