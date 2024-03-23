package sapo.edu.jpa.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Table(name = "warehouse")
@Entity

public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Danh mục id không được trống")
    private Long id;

    @Column(name = "warehouse_code")
    @NotBlank(message = "Danh mục warehouse code không được trống")
    private String warehouseCode;

    @NotBlank(message = "Danh mục tên không được trống")
    private String name;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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


    @NotBlank(message = "Danh mục địa điểm không được trống")
    private String location;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    public void updateFrom(Warehouse warehouse) {
        this.setWarehouseCode(warehouse.getWarehouseCode());
        this.setName(warehouse.getName());
        this.setLocation(warehouse.getLocation());
        this.setCreatedDate(warehouse.getCreatedDate());
        this.setUpdatedDate(warehouse.getUpdatedDate());
    }

    // Getter và Setter
}