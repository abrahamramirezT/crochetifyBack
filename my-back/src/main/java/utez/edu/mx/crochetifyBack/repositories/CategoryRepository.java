package utez.edu.mx.crochetifyBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.crochetifyBack.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}