package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id_category", nullable = false, length = 36)
    private String idCategory;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;


}
