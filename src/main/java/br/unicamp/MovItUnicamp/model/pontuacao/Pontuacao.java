package br.unicamp.MovItUnicamp.model.pontuacao;

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
@Table(name = "tbpontuacao")
public class Pontuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "corrida_id")
    private Corrida corrida;

    private Integer pontos;

    public Pontuacao(Usuario usuario, Corrida corrida, Integer pontos) {
        this.usuario = usuario;
        this.corrida = corrida;
        this.pontos = pontos;
    }
}
