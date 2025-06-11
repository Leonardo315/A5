// CorridaService.java
package br.unicamp.MovItUnicamp.model.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.reserva.Reserva;

import br.unicamp.MovItUnicamp.model.reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public Corrida iniciarCorrida(Integer reservaId, Double distanciaKm) {
        Reserva reserva = reservaRepository.findById(reservaId).orElse(null);
        Corrida corrida = new Corrida(
                reserva,
                LocalDateTime.now(),
                null,
                distanciaKm
        );
        return corridaRepository.save(corrida);
    }

    @Transactional
    public Corrida encerrarCorrida(Integer corridaId, Double distanciaKm) {
        Corrida corrida = corridaRepository.findById(corridaId).orElse(null);
        corrida.setDataFim(LocalDateTime.now());
        corrida.setDistanciaPercorrida(distanciaKm);
        return corridaRepository.save(corrida);
    }

    @Transactional(readOnly = true)
    public List<Corrida> listarCorridas() {
        return corridaRepository.findAll();
    }

    @Transactional
    public void excluirCorrida(Integer id) {
        corridaRepository.deleteById(id);
    }
}
