package sapo.edu.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sapo.edu.jpa.model.Product;
import sapo.edu.jpa.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByKeyAndCategory(String keyword, String categoryCode) {
        return productRepository.findByNameAndCategoryCode(keyword, categoryCode);
    }


    public List<Object[]> countProductsByCategory() {
        return productRepository.countProductsByCategory();
    }


    public List<Product> getTopSellingProducts(int limit) {
        return productRepository.findTop10ByQuantitySoldOrderByQuantitySoldDesc(limit);
    }

    public Page<Product> getProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void deleteProductsByCategory(String categoryCode) {
        List<Product> products = productRepository.findProductsByCategoryCode(categoryCode);
        for (Product product : products) {
            productRepository.deleteById(product.getId());
        }
    }
}
