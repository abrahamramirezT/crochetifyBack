package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private int quantity;

    @Column(name= "price")
    private double price;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id")  // Esto crea la clave foránea en la tabla 'pedido'
    private Product product;

}
