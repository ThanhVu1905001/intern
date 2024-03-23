package com.sapo.edu.demo;

import com.sapo.edu.demo.model.Product;
import com.sapo.edu.demo.model.Warehouse;
import com.sapo.edu.demo.service.CategoryService;
import com.sapo.edu.demo.service.ProductService;
import com.sapo.edu.demo.service.WarehouseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class DemoApplication {

    //tất cả dữ liệu đã được tự thêm trong code, nên không cần nhập. Chọn số trong menu vaf chương trình tự chạy

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService productService, CategoryService categoryService, WarehouseService warehouseService) {
        return args -> {
            displayMenu();
            processUserInput(productService, categoryService, warehouseService);
        };
    }

    private void displayMenu() {
        System.out.println("Welcome to the Inventory Management System");
        System.out.println("1. List Products");
        System.out.println("2. List Categories");
        System.out.println("3. List Warehouses");
        System.out.println("4. Add a New Warehouse");
        System.out.println("5. Update a Warehouse");
        System.out.println("6. Delete a Warehouse");
        System.out.println("7. Search Products by Key and Category");
        System.out.println("8. Count Products by Category");
        System.out.println("9. Delete Category and Products");
        System.out.println("10. List Top Selling Products");
        System.out.println("0. Exit");
    }

    private void processUserInput(ProductService productService, CategoryService categoryService, WarehouseService warehouseService) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your choice (0 to exit): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    listProducts(productService);
                    break;
                case "2":
                    listCategories(categoryService);
                    break;
                case "3":
                    listWarehouses(warehouseService);
                    break;
                case "4":
                    addNewWarehouse(warehouseService);
                    break;
                case "5":
                    System.out.print("Enter the Warehouse ID to update: ");
                    long warehouseId = Long.parseLong(scanner.nextLine());
                    System.out.print("Enter the new Warehouse name: ");
                    String newWarehouseName = scanner.nextLine();
                    updateWarehouse(warehouseService, warehouseId, newWarehouseName);
                    break;
                case "6":
                    System.out.print("Enter the Warehouse ID to delete: ");
                    String warehouseIdToDelete = scanner.nextLine();
                    deleteWarehouse(warehouseService, warehouseIdToDelete);
                    break;
                case "7":
                    printProductPhoneApple(productService);
                    break;
                case "8":
                    printNumberOfProduct(productService);
                    break;
                case "9":
                    System.out.print("Enter category code to delete: ");
                    String categoryCode = scanner.nextLine();
                    deleteCategoryAndProducts(categoryService,productService,categoryCode);
                    break;
                case "10":
                    int limit = 10;
                    printTopSellingProducts(productService, limit);
                    break;
                case "0":
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    private void listProducts(ProductService productService) {
        System.out.println("Product List:");
        productService.getAllProduct().forEach(product -> System.out.println(product));
    }

    private void listCategories(CategoryService categoryService) {
        System.out.println("Category List:");
        categoryService.getAllCategory().forEach(category -> System.out.println(category));
    }

    private void listWarehouses(WarehouseService warehouseService) {
        System.out.println("Warehouse List:");
        warehouseService.getAllWarehouses().forEach(warehouse -> System.out.println(warehouse));
    }

    private void addNewWarehouse(WarehouseService warehouseService) {
        long timeInMillis = System.currentTimeMillis();
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setWarehouseCode("WH123");
        newWarehouse.setName("New Warehouse");
        newWarehouse.setLocation("Location XYZ");
        newWarehouse.setCreatedDate(new Timestamp(timeInMillis));
        newWarehouse.setUpdatedDate(new Timestamp(timeInMillis));
        warehouseService.createWarehouse(newWarehouse);
        System.out.println("New warehouse added.");
    }
    private void updateWarehouse(WarehouseService warehouseService, long warehouseId, String newName) {
        Warehouse updatedWarehouse = warehouseService.getWarehouseById(warehouseId);
        if (updatedWarehouse != null) {
            updatedWarehouse.setName(newName);
            warehouseService.updateWarehouse(updatedWarehouse);
            System.out.println("Warehouse updated.");
        } else {
            System.out.println("Warehouse with ID " + warehouseId + " not found.");
        }
    }

    private void deleteWarehouse(WarehouseService warehouseService, String inputId) {
        try {
            Long id = Long.parseLong(inputId);
            Warehouse warehouse = warehouseService.findWarehouseById(id);
            if (warehouse != null) {
                warehouseService.deleteWarehouse(id);
                System.out.println("Warehouse with ID " + id + " deleted.");
            } else {
                System.out.println("Warehouse with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid warehouse ID. Please enter a number.");
        } catch (Exception e) {
            System.err.println("An error occurred while deleting the warehouse: " + e.getMessage());
        }
    }

    private void printProductPhoneApple(ProductService productService) {
        try {
            List<Product> products = productService.getProductsbyKeyandCat("Điện Thoại", "apple");
            if (products.isEmpty()) {
                System.out.println("No Apple phone products found.");
            } else {
                System.out.println("List of products Apple phone products:");
                products.forEach(product -> System.out.println(product));
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void printNumberOfProduct(ProductService productService){
        List<Object[]> productCountList = productService.countProductsByCategory();
        System.out.println("Number of products in each category (sorted descending):");
        for (Object[] row : productCountList) {
            String categoryName = (String) row[0];
            Long productCount = (Long) row[1];
            System.out.println("Category: " + categoryName + ", product number: " + productCount);
        }
    }
    private void deleteCategoryAndProducts(CategoryService categoryService, ProductService productService, String categoryCode) {
        try {
            // Kiểm tra xem danh mục có tồn tại hay không
            if (categoryService.doesCategoryExist(categoryCode)) {
                // Xóa danh mục và các sản phẩm trong danh mục
                categoryService.deleteCategoryAndProducts(categoryCode);
                System.out.println("Removed the category and the products in the category: " + categoryCode);
            } else {
                System.out.println("Category does not exist.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    private void printTopSellingProducts(ProductService productService, int limit) {
        List<Product> topSellingProducts = productService.getTopSellingProducts(limit);
        System.out.println(limit + " The product with the highest sales volume:");
        topSellingProducts.forEach(product -> System.out.println(product));
    }
}
