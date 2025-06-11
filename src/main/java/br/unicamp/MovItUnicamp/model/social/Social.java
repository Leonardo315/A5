package br.unicamp.MovItUnicamp.model.social;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbsocial")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "amigo_id")
    private Usuario amigo;

    @ManyToOne
    @JoinColumn(name = "corrida_id", nullable = true)
    private Corrida corrida;

    public Social(Usuario usuario, Usuario amigo) {
        this.usuario = usuario;
        this.amigo = amigo;
    }
}
