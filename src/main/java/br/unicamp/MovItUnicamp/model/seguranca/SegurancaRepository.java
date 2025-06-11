// SegurancaRepository.java
package br.unicamp.MovItUnicamp.model.seguranca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegurancaRepository extends JpaRepository<Seguranca, Integer> { }
