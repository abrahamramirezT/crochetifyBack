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
@Table(name = "shipment")
public class Shipment {

    @Id
    @Column(name = "id_shipment", nullable = false, length = 36)
    private String idShipment;


    @NotNull
    @Column(name = "status", nullable = false)
    private boolean status;

    @NotNull
    @Column(name = "shipping_day", nullable = false)
    private LocalDate shipping_day;

    @NotNull
    @Column(name = "delivery_day", nullable = false)
    private LocalDate delivery_day;





}
