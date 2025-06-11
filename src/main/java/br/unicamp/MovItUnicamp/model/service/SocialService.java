// SocialService.java
package br.unicamp.MovItUnicamp.model.service;

import br.unicamp.MovItUnicamp.model.corrida.Corrida;
import br.unicamp.MovItUnicamp.model.corrida.CorridaRepository;
import br.unicamp.MovItUnicamp.model.social.Social;
import br.unicamp.MovItUnicamp.model.social.SocialRepository;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialService {

    @Autowired
    private SocialRepository socialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CorridaRepository corridaRepository;

    @Transactional
    public Social adicionarAmigo(Integer usuarioId, Integer amigoId) {
        Usuario u = usuarioRepository.findById(usuarioId).orElse(null);
        Usuario amigo = usuarioRepository.findById(amigoId).orElse(null);
        Social s = new Social(u, amigo);
        return socialRepository.save(s);
    }

    @Transactional(readOnly = true)
    public List<Social> listarSocial() {
        return socialRepository.findAll();
    }

    @Transactional
    public Social iniciarCorridaComAmigo(Integer socialId, Integer corridaId) {
        Social s = socialRepository.findById(socialId).orElse(null);
        Corrida c = corridaRepository.findById(corridaId).orElse(null);
        s.setCorrida(c);
        return socialRepository.save(s);
    }

    @Transactional
    public void removerAmizade(Integer id) {
        socialRepository.deleteById(id);
    }
}
