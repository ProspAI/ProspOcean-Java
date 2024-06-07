package br.com.prospocean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sensor_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String tipoSensor;

    @NotNull
    @Column(nullable = false)
    private Double valor;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime timestamp;
}
