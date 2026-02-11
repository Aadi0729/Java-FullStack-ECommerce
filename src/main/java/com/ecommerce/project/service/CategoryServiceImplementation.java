package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L; // Keeping the track of id's

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        /*Setting the id manually here, always incremented. Even if the user is sending the
        id explicitly, then this value will be overridden.*/
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElse(null);

        if(category == null)
            return "Category with id " + categoryId + " not found";

        categories.remove(category);
        return "Category with categoryId: " + categoryId + " has been deleted successfully";
    }
}
