package com.sapo.edu.demo.repository;

import com.sapo.edu.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
    public class CategoryRepository {
        private final JdbcTemplate jdbcTemplate;
        private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("category").usingGeneratedKeyColumns("id");
    }

    public List<Category> findAllCategory() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    public Category findCategoryById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Category.class));
    }

    public Category createCategory(Category category) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", category.getId());
        data.put("category_code", category.getCategoryCode());
        data.put("name", category.getName());
        data.put("category_description", category.getCategoryDescription());
        data.put("created_date", category.getCreatedDate());
        data.put("updated_date", category.getUpdatedDate());
        Number key = simpleJdbcInsert.executeAndReturnKey(data);
        category.setId(key.longValue());
        return category;
    }

        public Category updatecategory(Category category) {
            String sql = "UPDATE category SET category_code=?,name = ?, category_description = ?, created_date = ?, updated_date=? WHERE id = ?";
            jdbcTemplate.update(sql, category.getId(),  category.getCategoryCode(), category.getName(), category.getCategoryDescription(),category.getCreatedDate(),category.getUpdatedDate());
            return category;
        }

        public void deleteById(Long id) {
            String sql = "DELETE FROM category WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }

        public void deleteCategoryAndProducts(String categoryCode) {
            String deleteProductsSQL = "DELETE FROM product WHERE category_code = ?";
            String deleteCategorySQL = "DELETE FROM category WHERE category_code = ?";
            jdbcTemplate.update(deleteProductsSQL, categoryCode);
            jdbcTemplate.update(deleteCategorySQL, categoryCode);
        }


        public boolean doesCategoryExist(String categoryCode) {
            try {
                // Tạo truy vấn SQL để kiểm tra sự tồn tại của danh mục
                String sql = "SELECT COUNT(*) FROM category WHERE category_code = ?";
                int count = jdbcTemplate.queryForObject(sql, Integer.class, categoryCode);
                return count > 0;
            } catch (Exception e) {
                // Xử lý lỗi
                e.printStackTrace();
                return false;
            }
        }
    }