package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Direction;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {


    List<Direction> findByUser_IdUser(Long idUser);
}
