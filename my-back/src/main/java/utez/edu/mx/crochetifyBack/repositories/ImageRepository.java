package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Image;
import utez.edu.mx.crochetifyBack.entities.Stock;

public interface ImageRepository extends JpaRepository<Image,Long> {
    void deleteByStock(Stock updatedStock);
}
