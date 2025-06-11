// SocialRepository.java
package br.unicamp.MovItUnicamp.model.social;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<Social, Integer> { }
