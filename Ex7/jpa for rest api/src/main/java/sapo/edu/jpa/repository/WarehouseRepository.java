package sapo.edu.jpa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapo.edu.jpa.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Page<Warehouse> findByNameContaining(String name, Pageable pageable);

}
