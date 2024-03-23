package sapo.edu.jpa.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}