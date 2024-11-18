package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id_review", nullable = false, length = 36)
    private Long idReview;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "comment", nullable = false, length = 255)
    private int comment;


}
