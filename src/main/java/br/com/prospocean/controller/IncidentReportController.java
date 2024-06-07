package br.com.prospocean.controller;

import br.com.prospocean.dto.request.IncidentReportRequestDTO;
import br.com.prospocean.dto.response.IncidentReportResponseDTO;
import br.com.prospocean.service.IncidentReportService;
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
@RequestMapping("/api/incident-reports")
public class IncidentReportController {

    @Autowired
    private IncidentReportService incidentReportService;

    @PostMapping
    public ResponseEntity<IncidentReportResponseDTO> criarIncidentReport(@RequestBody @Valid IncidentReportRequestDTO incidentReportRequestDTO) {
        IncidentReportResponseDTO responseDTO = incidentReportService.criarIncidentReport(incidentReportRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentReportResponseDTO> atualizarIncidentReport(@PathVariable UUID id, @RequestBody @Valid IncidentReportRequestDTO incidentReportRequestDTO) {
        IncidentReportResponseDTO responseDTO = incidentReportService.atualizarIncidentReport(id, incidentReportRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<IncidentReportResponseDTO>> obterIncidentReportPorId(@PathVariable UUID id) {
        IncidentReportResponseDTO responseDTO = incidentReportService.obterIncidentReportPorId(id);
        EntityModel<IncidentReportResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IncidentReportController.class).obterIncidentReportPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIncidentReport(@PathVariable UUID id) {
        incidentReportService.deletarIncidentReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<IncidentReportResponseDTO>> listarIncidentReports(@RequestParam(defaultValue = "0") int pagina,
                                                                                 @RequestParam(defaultValue = "10") int tamanho) {
        Page<IncidentReportResponseDTO> incidentReports = incidentReportService.listarIncidentReports(pagina, tamanho);
        return ResponseEntity.ok(incidentReports);
    }
}
