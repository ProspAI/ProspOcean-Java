package br.com.prospocean.service;

import br.com.prospocean.controller.IncidentReportController;
import br.com.prospocean.dto.request.IncidentReportRequestDTO;
import br.com.prospocean.dto.response.IncidentReportResponseDTO;
import br.com.prospocean.model.IncidentReport;
import br.com.prospocean.repository.IncidentReportRepository;
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
public class IncidentReportService {

    @Autowired
    private IncidentReportRepository incidentReportRepository;

    public IncidentReportResponseDTO criarIncidentReport(IncidentReportRequestDTO incidentReportRequestDTO) {
        IncidentReport incidentReport = new IncidentReport();
        incidentReport.setDescricao(incidentReportRequestDTO.getDescricao());
        incidentReport.setLocalizacao(incidentReportRequestDTO.getLocalizacao());
        incidentReport.setFotos(incidentReportRequestDTO.getFotos());
        incidentReport.setTimestamp(incidentReportRequestDTO.getTimestamp());
        incidentReport.setStatus(incidentReportRequestDTO.getStatus());

        IncidentReport salvo = incidentReportRepository.save(incidentReport);

        return new IncidentReportResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getLocalizacao(), salvo.getFotos(), salvo.getTimestamp(), salvo.getStatus());
    }

    public IncidentReportResponseDTO atualizarIncidentReport(UUID id, IncidentReportRequestDTO incidentReportRequestDTO) {
        IncidentReport incidentReport = incidentReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório de incidente não encontrado"));

        incidentReport.setDescricao(incidentReportRequestDTO.getDescricao());
        incidentReport.setLocalizacao(incidentReportRequestDTO.getLocalizacao());
        incidentReport.setFotos(incidentReportRequestDTO.getFotos());
        incidentReport.setTimestamp(incidentReportRequestDTO.getTimestamp());
        incidentReport.setStatus(incidentReportRequestDTO.getStatus());

        IncidentReport salvo = incidentReportRepository.save(incidentReport);

        return new IncidentReportResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getLocalizacao(), salvo.getFotos(), salvo.getTimestamp(), salvo.getStatus());
    }

    public IncidentReportResponseDTO obterIncidentReportPorId(UUID id) {
        IncidentReport incidentReport = incidentReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório de incidente não encontrado"));

        return new IncidentReportResponseDTO(incidentReport.getId(), incidentReport.getDescricao(), incidentReport.getLocalizacao(), incidentReport.getFotos(), incidentReport.getTimestamp(), incidentReport.getStatus());
    }

    public void deletarIncidentReport(UUID id) {
        IncidentReport incidentReport = incidentReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório de incidente não encontrado"));
        incidentReportRepository.delete(incidentReport);
    }

    public Page<IncidentReportResponseDTO> listarIncidentReports(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<IncidentReport> incidentReports = incidentReportRepository.findAll(pageable);
        return incidentReports.map(incidentReport -> new IncidentReportResponseDTO(incidentReport.getId(), incidentReport.getDescricao(), incidentReport.getLocalizacao(), incidentReport.getFotos(), incidentReport.getTimestamp(), incidentReport.getStatus()));
    }

    public EntityModel<IncidentReportResponseDTO> adicionarLinksHATEOAS(UUID id, IncidentReportResponseDTO responseDTO) {
        EntityModel<IncidentReportResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IncidentReportController.class).obterIncidentReportPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
