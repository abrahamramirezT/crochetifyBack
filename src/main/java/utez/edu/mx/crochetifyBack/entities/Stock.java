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
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id_stock", nullable = false, length = 36)
    private String idStock;

    @NotNull
    @Column(name = "color", nullable = false, length = 255)
    private String color;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "image", nullable = false, length = 255)
    private String image;

    @NotNull
    @Column(name = "status", nullable = false)
    private boolean status;

}
