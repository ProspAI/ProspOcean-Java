package br.com.prospocean.repository;

import br.com.prospocean.model.EspecieMarinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EspecieMarinhaRepository extends JpaRepository<EspecieMarinha, UUID> {
}
