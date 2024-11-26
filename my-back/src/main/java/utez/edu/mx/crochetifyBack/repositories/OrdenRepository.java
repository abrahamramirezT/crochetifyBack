package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Orden;

public interface OrdenRepository extends JpaRepository<Orden,Long> {
}
