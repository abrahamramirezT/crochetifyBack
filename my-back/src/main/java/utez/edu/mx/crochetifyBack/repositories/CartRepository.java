package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utez.edu.mx.crochetifyBack.entities.Cart;

@Repository
public interface CartRepository  extends JpaRepository<Cart,Long> {

}
