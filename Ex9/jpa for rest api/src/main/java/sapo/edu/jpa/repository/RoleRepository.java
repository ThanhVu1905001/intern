package sapo.edu.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapo.edu.jpa.model.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
