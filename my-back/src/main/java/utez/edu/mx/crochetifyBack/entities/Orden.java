package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @Column(name = "id_orden", nullable = false, length = 36)
    private Long idOrden;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "total", nullable = false)
    private double total;


    @Column(name = "purchase_day", nullable = false)
    private LocalDate purchase_day;




}
