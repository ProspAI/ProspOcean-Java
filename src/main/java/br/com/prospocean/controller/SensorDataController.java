package br.com.prospocean.controller;

import br.com.prospocean.dto.request.SensorDataRequestDTO;
import br.com.prospocean.dto.response.SensorDataResponseDTO;
import br.com.prospocean.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sensordata")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<SensorDataResponseDTO> criarSensorData(@RequestBody @Valid SensorDataRequestDTO sensorDataRequestDTO) {
        SensorDataResponseDTO responseDTO = sensorDataService.criarSensorData(sensorDataRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorDataResponseDTO> atualizarSensorData(@PathVariable UUID id, @RequestBody @Valid SensorDataRequestDTO sensorDataRequestDTO) {
        SensorDataResponseDTO responseDTO = sensorDataService.atualizarSensorData(id, sensorDataRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SensorDataResponseDTO>> obterSensorDataPorId(@PathVariable UUID id) {
        SensorDataResponseDTO responseDTO = sensorDataService.obterSensorDataPorId(id);
        EntityModel<SensorDataResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SensorDataController.class).obterSensorDataPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSensorData(@PathVariable UUID id) {
        sensorDataService.deletarSensorData(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<SensorDataResponseDTO>> listarSensorData(@RequestParam(defaultValue = "0") int pagina,
                                                                        @RequestParam(defaultValue = "10") int tamanho) {
        Page<SensorDataResponseDTO> sensorData = sensorDataService.listarSensorData(pagina, tamanho);
        return ResponseEntity.ok(sensorData);
    }
}
