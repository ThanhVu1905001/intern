package sapo.edu.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapo.edu.jpa.model.Category;
import sapo.edu.jpa.model.Warehouse;
import sapo.edu.jpa.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;


    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
         return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void deleteCategoryAndProducts(String categoryCode) {
        // Xoá danh mục
        categoryRepository.deleteByCategoryCode(categoryCode);

        // Xoá sản phẩm thuộc danh mục
        productService.deleteProductsByCategory(categoryCode);
    }
    public Page<Category> getCategoryByName(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }
    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

}

