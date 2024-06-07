package br.com.prospocean.repository;

import br.com.prospocean.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, UUID> {
}
