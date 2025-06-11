// ReservaRepository.java
package br.unicamp.MovItUnicamp.model.reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> { }
