package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @Column(name = "id_orden", nullable = false, length = 36)
    private String idOrden;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "total", nullable = false)
    private double total;


    @Column(name = "purchase_day", nullable = false)
    private LocalDate purchase_day;


}
