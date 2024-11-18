package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
