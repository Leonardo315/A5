// PontuacaoService.java
package br.unicamp.MovItUnicamp.model.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.pontuacao.Pontuacao;
import br.unicamp.MovItUnicamp.model.pontuacao.PontuacaoRepository;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;

import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PontuacaoService {

    @Autowired
    private PontuacaoRepository pontuacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Transactional
    public Pontuacao gerarPontuacao(Integer corridaId) {
        Corrida corrida = corridaRepository.findById(corridaId).orElse(null);
        Usuario usuario = corrida.getReserva().getUsuario();
        int pontos = (int) Math.round(corrida.getDistanciaPercorrida() * 10);
        Pontuacao p = new Pontuacao(usuario, corrida, pontos);
        return pontuacaoRepository.save(p);
    }

    @Transactional(readOnly = true)
    public List<Pontuacao> listarPontuacoes() {
        return pontuacaoRepository.findAll();
    }

    @Transactional
    public void excluirPontuacao(Integer id) {
        pontuacaoRepository.deleteById(id);
    }
}
