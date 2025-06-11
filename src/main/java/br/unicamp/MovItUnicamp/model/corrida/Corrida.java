package br.unicamp.MovItUnicamp.model.corrida;

import br.unicamp.MovItUnicamp.model.reserva.Reserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbcorrida")
public class Corrida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relação com Reserva (onde está o usuário)
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Double distanciaPercorrida;

    @OneToMany(mappedBy = "corrida")
    private List<br.unicamp.MovItUnicamp.model.pontuacao.Pontuacao> pontuacoes;

    @OneToMany(mappedBy = "corrida")
    private List<br.unicamp.MovItUnicamp.model.seguranca.Seguranca> incidentes;

    @OneToMany(mappedBy = "corrida")
    private List<br.unicamp.MovItUnicamp.model.social.Social> socialCorridas;

    public Corrida(Reserva reserva, LocalDateTime dataInicio, LocalDateTime dataFim, Double distanciaPercorrida) {
        this.reserva = reserva;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.distanciaPercorrida = distanciaPercorrida;
    }
}
