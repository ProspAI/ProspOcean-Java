package br.com.prospocean.repository;

import br.com.prospocean.model.ConteudoEducacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConteudoEducacionalRepository extends JpaRepository<ConteudoEducacional, UUID> {
}
