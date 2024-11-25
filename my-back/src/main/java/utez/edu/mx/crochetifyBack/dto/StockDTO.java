package utez.edu.mx.crochetifyBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {
    private Long stockId;
    private String color;
    private int quantity; 
    private ProductDTO product;
}
