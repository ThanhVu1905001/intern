package sapo.edu.jpa.model;


import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Tên danh mục không được trống")
    private Long id;

    @Column(name = "category_code")
    @NotBlank(message = "Tên danh mục không được trống")
    private String categoryCode;

    @NotBlank(message = "Tên danh mục không được trống")
    private String name;

    @Column(name = "category_description")
    private String categoryDescription;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    // Getter và Setter

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", name='" + name + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
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
}
