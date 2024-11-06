package utez.edu.mx.crochetifyBack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table( name = "user")
public class User {
    @Id
    @Column(name = "id_user", nullable = false, length = 36)
    private String idUser;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password",nullable = false, length = 255)
    private String password;

    @Column(name = "status")
    private boolean status;

    @Column(name = "image", nullable = false, length = 255)
    private String image;


}
