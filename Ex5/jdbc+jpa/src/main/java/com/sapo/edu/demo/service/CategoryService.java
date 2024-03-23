package com.sapo.edu.demo.service;

import com.sapo.edu.demo.model.Category;
import com.sapo.edu.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }
    public Category createCategory(Category category) {
        return categoryRepository.createCategory(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.updatecategory(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteCategoryAndProducts(String categoryCode) {
        categoryRepository.deleteCategoryAndProducts(categoryCode);
    }

    public boolean doesCategoryExist(String categoryCode) {
        return categoryRepository.doesCategoryExist(categoryCode);
    }
}

