package utez.edu.mx.crochetifyBack.dto.requests.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateRequest {
    private Long idUser;
    private Long idStock;
    private int quantity;
}

