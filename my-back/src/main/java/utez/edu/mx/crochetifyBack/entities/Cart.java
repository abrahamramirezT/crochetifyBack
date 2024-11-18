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
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "id_cart", nullable = false, length = 36)
    private Long idCart;

    @Column(name = "total", nullable = false, length = 255)
    private double total;




}
