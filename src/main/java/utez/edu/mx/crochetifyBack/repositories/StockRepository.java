package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Stock;

public interface StockRepository extends JpaRepository<Stock,Long> {

}