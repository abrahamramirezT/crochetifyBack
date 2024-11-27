package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @Column(name = "score")
    private int score;

    @Column(name = "comment")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
}
