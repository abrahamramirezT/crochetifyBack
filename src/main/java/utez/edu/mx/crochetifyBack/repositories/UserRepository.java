package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
