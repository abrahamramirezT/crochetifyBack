package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @Column(name = "id_shipment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipment;


    @Column(name = "status")
    private int status;

    @Column(name = "shipping_day")
    private LocalDate shipping_day;

    @Column(name = "delivery_day")
    private LocalDate delivery_day;

    @OneToOne
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")

    private Orden orden;



}
