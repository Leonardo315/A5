// PontuacaoRepository.java
package br.unicamp.MovItUnicamp.model.pontuacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Integer> { }
