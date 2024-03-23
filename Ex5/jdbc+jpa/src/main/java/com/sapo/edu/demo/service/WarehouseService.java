package com.sapo.edu.demo.service;

import com.sapo.edu.demo.model.Warehouse;
import com.sapo.edu.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public Warehouse findWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }
}
