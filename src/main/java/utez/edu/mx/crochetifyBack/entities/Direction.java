package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table( name = "user")

public class Direction {

    @Id
    @Column(name = "id_direction", nullable = false, length = 36)
    private String idDirection;

    @NotNull
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;



}
