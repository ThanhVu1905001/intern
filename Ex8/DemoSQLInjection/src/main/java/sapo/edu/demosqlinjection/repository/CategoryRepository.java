package sapo.edu.demosqlinjection.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sapo.edu.demosqlinjection.model.Category;

import java.util.List;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //hàm này sẽ dễ dàng bị tấn công bởi SQL Injection do đã nối chuỗi categoryName trực tiếp vào câu truy vấn SQL
    public List<Category> searchCategoryUnSafe(String categoryName) {
        String sql = "SELECT * FROM category WHERE name = " + categoryName ;
        List<Category> categories = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Category category = new Category();
            category.setId(resultSet.getLong("id"));
            category.setCategoryCode(resultSet.getString("category_code"));
            category.setName(resultSet.getString("name"));
            return category;
        });
        return categories;
    }


    // Dưới đây là hàm đã được tinh chỉnh để đối phó với SQL injection
    // bằng jdbcTemplate.query() sử dụng một prepared statement với
    // placeholder ? để thực hiện truy vấn. Giá trị của categoryName được truyền
    // vào như một tham số trong mảng Object[], đảm bảo rằng nó sẽ không gây ra
    // lỗ hổng SQL injection.

    public List<Category> searchCategorySafe(String categoryName) {
        String sql = "SELECT * FROM category WHERE name = ?";
        List<Category> categories = jdbcTemplate.query(sql, new Object[]{categoryName}, (resultSet, rowNum) -> {
            Category category = new Category();
            category.setId(resultSet.getLong("id"));
            category.setCategoryCode(resultSet.getString("category_code"));
            category.setName(resultSet.getString("name"));
            return category;
        });
        return categories;
    }
}

