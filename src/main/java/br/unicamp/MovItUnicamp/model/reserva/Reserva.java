package br.unicamp.MovItUnicamp.model.reserva;

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
@Table(name = "tbreserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "bicicleta_id")
    private Integer bicicletaId;


    private LocalDateTime dataReserva;

    public Reserva(Usuario usuario, Integer bicicletaId) {
        this.usuario = usuario;
        this.bicicletaId = bicicletaId;
        this.dataReserva = LocalDateTime.now();
    }
}
