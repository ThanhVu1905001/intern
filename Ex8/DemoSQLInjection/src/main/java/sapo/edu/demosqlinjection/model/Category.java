package sapo.edu.demosqlinjection.model;

public class Category {
    private Long id;
    private String categoryCode;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", name='" + name + '\'' +
                '}';
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
}

