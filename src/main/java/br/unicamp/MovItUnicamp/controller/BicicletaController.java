package br.unicamp.MovItUnicamp.controller;

import br.unicamp.MovItUnicamp.model.bicicletas.Bicicleta;
import br.unicamp.MovItUnicamp.model.bicicletas.BicicletaRepository;
import br.unicamp.MovItUnicamp.model.bicicletas.DadosCadastroBicicleta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bicicleta")
public class BicicletaController {


    @Autowired
    private BicicletaRepository bicicletaRepository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroBicicleta dadosCadastroBicicleta){
        bicicletaRepository.save(new Bicicleta(dadosCadastroBicicleta));
    }

    @GetMapping
    public List<Bicicleta> listar(){
        return bicicletaRepository.findAll();
    }
}
