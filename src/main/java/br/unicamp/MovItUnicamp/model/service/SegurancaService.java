// SegurancaService.java
package br.unicamp.MovItUnicamp.model.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.seguranca.Seguranca;
import br.unicamp.MovItUnicamp.model.seguranca.SegurancaRepository;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SegurancaService {

    @Autowired
    private SegurancaRepository segurancaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Transactional
    public Seguranca registrarIncidente(Integer usuarioId, Integer corridaId, String descricao) {
        Usuario u = usuarioRepository.findById(usuarioId).orElse(null);
        Corrida c = corridaRepository.findById(corridaId).orElse(null);
        Seguranca seg = new Seguranca(u, c, LocalDateTime.now(), descricao);
        return segurancaRepository.save(seg);
    }

    @Transactional(readOnly = true)
    public List<Seguranca> listarIncidentes() {
        return segurancaRepository.findAll();
    }

    @Transactional
    public void removerIncidente(Integer id) {
        segurancaRepository.deleteById(id);
    }
}
