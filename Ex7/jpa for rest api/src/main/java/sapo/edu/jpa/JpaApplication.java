package sapo.edu.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sapo.edu.jpa.model.Category;
import sapo.edu.jpa.model.Product;
import sapo.edu.jpa.model.Warehouse;
import sapo.edu.jpa.service.CategoryService;
import sapo.edu.jpa.service.ProductService;
import sapo.edu.jpa.service.WarehouseService;

import java.sql.Timestamp;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

}
