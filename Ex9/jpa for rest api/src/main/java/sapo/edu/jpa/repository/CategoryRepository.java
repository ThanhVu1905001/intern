package sapo.edu.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapo.edu.jpa.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    // Xóa Category bằng categoryCode
    void deleteByCategoryCode(String categoryCode);

    Page<Category> findByNameContaining(String name, Pageable pageable);
}

