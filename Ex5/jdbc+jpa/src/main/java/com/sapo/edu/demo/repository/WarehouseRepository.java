package com.sapo.edu.demo.repository;


import com.sapo.edu.demo.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {


}


