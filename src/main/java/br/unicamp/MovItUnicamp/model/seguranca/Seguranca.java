package br.unicamp.MovItUnicamp.model.seguranca;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbseguranca")
public class Seguranca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "corrida_id")
    private Corrida corrida;

    private LocalDateTime dataIncidente;
    private String descricao;

    public Seguranca(Usuario usuario, Corrida corrida, LocalDateTime dataIncidente, String descricao) {
        this.usuario = usuario;
        this.corrida = corrida;
        this.dataIncidente = dataIncidente;
        this.descricao = descricao;
    }
}
