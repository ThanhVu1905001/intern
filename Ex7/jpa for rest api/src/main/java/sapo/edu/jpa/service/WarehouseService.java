package sapo.edu.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sapo.edu.jpa.model.Warehouse;
import sapo.edu.jpa.repository.WarehouseRepository;

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
    public Page<Warehouse> getWarehouseByName(String name, Pageable pageable) {
        return warehouseRepository.findByNameContaining(name, pageable);
    }
    public Page<Warehouse> getAllWarehouses(Pageable pageable) {
        return warehouseRepository.findAll(pageable);
    }


}
