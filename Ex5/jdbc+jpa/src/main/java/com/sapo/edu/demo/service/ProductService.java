package com.sapo.edu.demo.service;

import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.model.Warehouse;
import com.sapo.edu.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAllProduct();
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsbyKeyandCat(String keyword, String categoryCode){return productRepository.getProductsbyKeyandCat(keyword, categoryCode);}

    public List<Object[]> countProductsByCategory(){return productRepository.countProductsByCategory();}
    public List<Product> getTopSellingProducts(int limit) {
        return productRepository.getTopSellingProducts(limit);
    }
}
