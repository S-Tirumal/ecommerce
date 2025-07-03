package com.ecom.productcatalogue.repository;

import com.ecom.productcatalogue.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCategoryRepositoryIsNotNull() {
        // This test will check if the application context loads successfully
        // and the CategoryRepository bean is created.
        assert categoryRepository != null;
    }

    @Test
    public void saveCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setTitle("Iphone");
        category1.setId(100L);
        category1.setDescription("latest Iphone");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category1.setId(2L);
        category2.setTitle("Macbook");
        category2.setId(1021L);
        category2.setDescription("latest mac");
        categoryRepository.save(category2);

        List<Category> categories = categoryRepository.findAll();
        assert categories.size() == 2 : "Expected 2 categories, but found " + categories.size();
        assert categories.get(0).getTitle().equals("Iphone") : "First category should be Iphone";
        assert categories.get(1).getTitle().equals("Macbook") : "Second category should be Macbook";

    }
}
