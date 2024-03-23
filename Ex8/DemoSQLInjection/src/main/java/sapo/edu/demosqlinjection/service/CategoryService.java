package sapo.edu.demosqlinjection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapo.edu.demosqlinjection.model.Category;
import sapo.edu.demosqlinjection.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> searchCategorySafe(String categoryName) {
        return categoryRepository.searchCategorySafe(categoryName);
    }

    public List<Category> searchCategoryUnsafe(String categoryName) {
        return categoryRepository.searchCategoryUnSafe(categoryName);
    }
}

