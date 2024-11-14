package utez.edu.mx.crochetifyBack.dto.requests.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StockCreateRequest {

    private String color;
    private int quantity;
    private double price;
    private Long productId;
}