// CorridaRepository.java
package br.unicamp.MovItUnicamp.model.corrida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorridaRepository extends JpaRepository<Corrida, Integer> { }
