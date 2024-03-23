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
import sapo.edu.jpa.model.Category;
import sapo.edu.jpa.model.Warehouse;
import sapo.edu.jpa.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id,@Valid @RequestBody Category category) {
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory == null) {
            // Xử lý trường hợp không tìm thấy danh mục
            return null;
        }

        category.setId(id);
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteCategory(@PathVariable(value = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/{categoryCode}")
    public ResponseEntity<String> deleteCategoryAndProducts(@PathVariable String categoryCode) {
        try {
            categoryService.deleteCategoryAndProducts(categoryCode);
            return ResponseEntity.ok("Danh mục và sản phẩm đã được xoá thành công.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi xoá danh mục và sản phẩm: " + e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<Page<Category>> getCategory(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        int pageSize = 10; // Số sản phẩm trên mỗi trang

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

        if (name != null) {
            // Lọc theo tên sản phẩm
            Page<Category> categories = categoryService.getCategoryByName(name, pageable);
            return ResponseEntity.ok(categories);
        } else {
            // Không có tên sản phẩm nào được cung cấp, trả về tất cả sản phẩm trên trang hiện tại
            Page<Category> categories = categoryService.getAllCategory(pageable);
            return ResponseEntity.ok(categories);
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

