package br.com.prospocean.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SensorDataResponseDTO {

    private UUID id;
    private String tipoSensor;
    private Double valor;
    private LocalDateTime timestamp;

    public SensorDataResponseDTO(UUID id, String tipoSensor, Double valor, LocalDateTime timestamp) {
        this.id = id;
        this.tipoSensor = tipoSensor;
        this.valor = valor;
        this.timestamp = timestamp;

    }
}
