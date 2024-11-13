package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @ManyToMany
    @JoinTable(
            name = "product_category", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "product_id"), // Clave foránea hacia Product
            inverseJoinColumns = @JoinColumn(name = "category_id") // Clave foránea hacia Category
    )
    private Set<Category> categories;


}
