package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table( name = "direction")
public class Direction {

    @Id
    @Column(name = "id_direction", nullable = false, length = 36)
    private Long idDirection;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;



}
