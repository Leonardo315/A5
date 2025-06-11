package br.unicamp.MovItUnicamp.controller;

import br.unicamp.MovItUnicamp.model.usuario.DadosCadastroUsuario;
import br.unicamp.MovItUnicamp.model.usuario.Usuario;
import br.unicamp.MovItUnicamp.model.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario){
        usuarioRepository.save(new Usuario(dadosCadastroUsuario));
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
}
