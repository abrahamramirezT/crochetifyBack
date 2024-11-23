package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
