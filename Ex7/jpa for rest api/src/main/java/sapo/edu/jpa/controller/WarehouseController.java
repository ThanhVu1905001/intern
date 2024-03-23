package sapo.edu.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sapo.edu.jpa.model.Warehouse;
import sapo.edu.jpa.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/")
    public ResponseEntity<List<Warehouse>> listAllWarehouses() {
        List<Warehouse> listWarehouses = warehouseService.getAllWarehouses();
        if (listWarehouses.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listWarehouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Warehouse findWarehouse(@PathVariable("id") Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) {
            ResponseEntity.notFound().build();
        }
        return warehouse;
    }

    @PostMapping("/")
    public Warehouse saveWarehouse(@Valid @RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable(value = "id") Long warehouseId,
                                                      @Valid @RequestBody Warehouse warehouseForm) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }
        // Cập nhật dữ liệu trong warehouseForm vào warehouse
        warehouse.updateFrom(warehouseForm);

        Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse);
        return ResponseEntity.ok(updatedWarehouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable(value = "id") Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }

        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<Page<Warehouse>> getWarehouse(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        int pageSize = 10; // Số sản phẩm trên mỗi trang

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        if (name != null) {
            // Lọc theo tên sản phẩm
            Page<Warehouse> warehouses = warehouseService.getWarehouseByName(name, pageable);
            return ResponseEntity.ok(warehouses);
        } else {
            // Không có tên sản phẩm nào được cung cấp, trả về tất cả sản phẩm trên trang hiện tại
            Page<Warehouse> products = warehouseService.getAllWarehouses(pageable);
            return ResponseEntity.ok(products);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Lấy danh sách các lỗi validation
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : errors) {
            errorMessage.append(error.getDefaultMessage()).append("; ");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
