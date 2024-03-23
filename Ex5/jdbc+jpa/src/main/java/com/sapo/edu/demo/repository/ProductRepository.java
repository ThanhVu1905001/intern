package com.sapo.edu.demo.repository;

import com.sapo.edu.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Product> findAllProduct() {
        String sql = "SELECT * FROM product";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findProductById(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product createProduct(Product product) {
        String sql = "INSERT INTO product (id, product_code, category_code, warehouse_code, name, price, product_description, image_path, quantity_in_stock, quantity_sold, created_date, updated_date) VALUES (:id, :product_code, :category_code, :warehouse_code, :name, :price, :product_description, :image_path, :quantity_in_stock, :quantity_sold, :created_date, :updated_date)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", product.getId());
        params.put("product_code", product.getProductCode());
        params.put("category_code", product.getCategoryCode());
        params.put("warehouse_code", product.getWarehouseCode());
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("product_description", product.getProductDescription());
        params.put("image_path", product.getImagePath());
        params.put("quantity_in_stock", product.getQuantityInStock());
        params.put("quantity_sold", product.getQuantitySold());
        params.put("created_date", product.getCreatedDate());
        params.put("updated_date", product.getUpdatedDate());
        namedParameterJdbcTemplate.update(sql, params);
        return product;
    }

    //thay đổi bản ghi
    public Product updateProduct(Product product) {
        String sql = "UPDATE product SET product_code=?, category_code=?, warehouse_code=?, name=?, price=?, product_description=?, image_path=?, quantity_in_stock=?, quantity_sold=?, created_date=?, updated_date=? WHERE id = ?";
        jdbcTemplate.update(sql,  product.getId(), product.getProductCode(),product.getCategoryCode(),product.getWarehouseCode(), product.getName(),product.getPrice(),product.getProductDescription(),product.getImagePath(),product.getQuantityInStock(),product.getQuantitySold(),product.getCreatedDate(),product.getUpdatedDate());
        return product;
    }

    //xóa bản ghi
    public void deleteById(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    //lấy bản ghi product bằng keyword và category
    public List<Product> getProductsbyKeyandCat(String keyword, String categoryCode) {
        String sql = "SELECT * FROM product " +
                "WHERE name LIKE ? " +
                "AND category_code = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), "%" + keyword + "%", categoryCode);
    }
    public List<Object[]> countProductsByCategory() {
        String sql = "SELECT category_code, COUNT(*) AS product_count " +
                "FROM product " +
                "GROUP BY category_code " +
                "ORDER BY product_count DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String categoryCode = rs.getString("category_code");
            Long productCount = rs.getLong("product_count");
            return new Object[]{categoryCode, productCount};
        });
    }
    public List<Product> getTopSellingProducts(int limit) {
        String sql = "SELECT * FROM product ORDER BY quantity_sold DESC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), limit);
    }



}

