package sapo.edu.jpa.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Danh mục id không được trống")
    private Long id;

    @NotBlank(message = "Danh mục product code không được trống")
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "category_code")
    @NotBlank(message = "Danh mục category code không được trống")
    private String categoryCode;

    @Column(name = "warehouse_code")
    @NotBlank(message = "Danh mục warehouse code không được trống")
    private String warehouseCode;

    @NotBlank(message = "Danh mục tên không được trống")
    private String name;

    @NotBlank(message = "Danh mục giá không được trống")
    private double price;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "quantity_in_stock")
    private int quantityInStock;
    @Column(name = "quantity_sold")
    private int quantitySold;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "updated_date")
    private Timestamp updatedDate;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productDescription='" + productDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", quantitySold=" + quantitySold +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
