package sapo.edu.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sapo.edu.jpa.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryCode(String categoryCode);

    List<Product> findByNameAndCategoryCode(String keyword, String categoryCode);

    @Query("SELECT p.categoryCode, COUNT(p) AS productCount FROM Product p GROUP BY p.categoryCode ORDER BY productCount DESC")
    List<Object[]> countProductsByCategory();

    List<Product> findTop10ByQuantitySoldOrderByQuantitySoldDesc(int limit);

    Page<Product> findByNameContaining(String name, Pageable pageable);

    List<Product> findProductsByCategoryCode(String categoryCode);
}
