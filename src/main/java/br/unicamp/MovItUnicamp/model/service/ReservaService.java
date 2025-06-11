// ReservaService.java
package br.unicamp.MovItUnicamp.model.service;

import br.unicamp.MovItUnicamp.model.reserva.Reserva;
import br.unicamp.MovItUnicamp.model.reserva.ReservaRepository;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reserva buscarPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Reserva criarReserva(Integer usuarioId, Integer bicicletaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Reserva reserva = new Reserva(usuario, bicicletaId);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Integer id) {
        reservaRepository.deleteById(id);
    }
}
