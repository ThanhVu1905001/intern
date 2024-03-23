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
import sapo.edu.jpa.model.Product;
import sapo.edu.jpa.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top-selling")
    public List<Product> getTopSellingProducts(@RequestParam int limit) {
        return productService.getTopSellingProducts(limit);
    }
    @GetMapping("")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        int pageSize = 10; // Số sản phẩm trên mỗi trang

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        if (name != null) {
            // Lọc theo tên sản phẩm
            Page<Product> products = productService.getProductsByName(name, pageable);
            return ResponseEntity.ok(products);
        } else {
            // Không có tên sản phẩm nào được cung cấp, trả về tất cả sản phẩm trên trang hiện tại
            Page<Product> products = productService.getAllProducts(pageable);
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
