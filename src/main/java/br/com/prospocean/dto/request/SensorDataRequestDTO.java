package br.com.prospocean.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SensorDataRequestDTO {

    @NotNull
    private String tipoSensor;

    @NotNull
    private Double valor;

    @NotNull
    private LocalDateTime timestamp;
}
