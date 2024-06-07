package br.com.prospocean.service;

import br.com.prospocean.controller.SensorDataController;
import br.com.prospocean.dto.request.SensorDataRequestDTO;
import br.com.prospocean.dto.response.SensorDataResponseDTO;
import br.com.prospocean.model.SensorData;
import br.com.prospocean.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorDataResponseDTO criarSensorData(SensorDataRequestDTO sensorDataRequestDTO) {
        SensorData sensorData = new SensorData();
        sensorData.setTipoSensor(sensorDataRequestDTO.getTipoSensor());
        sensorData.setValor(sensorDataRequestDTO.getValor());
        sensorData.setTimestamp(sensorDataRequestDTO.getTimestamp());

        SensorData salvo = sensorDataRepository.save(sensorData);

        return new SensorDataResponseDTO(salvo.getId(), salvo.getTipoSensor(), salvo.getValor(), salvo.getTimestamp());
    }

    public SensorDataResponseDTO atualizarSensorData(UUID id, SensorDataRequestDTO sensorDataRequestDTO) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor data não encontrado"));

        sensorData.setTipoSensor(sensorDataRequestDTO.getTipoSensor());
        sensorData.setValor(sensorDataRequestDTO.getValor());
        sensorData.setTimestamp(sensorDataRequestDTO.getTimestamp());

        SensorData salvo = sensorDataRepository.save(sensorData);

        return new SensorDataResponseDTO(salvo.getId(), salvo.getTipoSensor(), salvo.getValor(), salvo.getTimestamp());
    }

    public SensorDataResponseDTO obterSensorDataPorId(UUID id) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor data não encontrado"));

        return new SensorDataResponseDTO(sensorData.getId(), sensorData.getTipoSensor(), sensorData.getValor(), sensorData.getTimestamp());
    }

    public void deletarSensorData(UUID id) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor data não encontrado"));
        sensorDataRepository.delete(sensorData);
    }

    public Page<SensorDataResponseDTO> listarSensorData(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<SensorData> sensorDataPage = sensorDataRepository.findAll(pageable);
        return sensorDataPage.map(sensorData -> new SensorDataResponseDTO(sensorData.getId(), sensorData.getTipoSensor(), sensorData.getValor(), sensorData.getTimestamp()));
    }

    public EntityModel<SensorDataResponseDTO> adicionarLinksHATEOAS(UUID id, SensorDataResponseDTO responseDTO) {
        EntityModel<SensorDataResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SensorDataController.class).obterSensorDataPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
