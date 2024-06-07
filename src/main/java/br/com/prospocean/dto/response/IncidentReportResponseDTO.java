package br.com.prospocean.dto.response;

import br.com.prospocean.model.IncidentReport;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class IncidentReportResponseDTO {

    private UUID id;
    private String descricao;
    private String localizacao;
    private List<String> fotos;
    private LocalDateTime timestamp;
    private IncidentReport.Status status;

    public IncidentReportResponseDTO(UUID id, String descricao, String localizacao, List<String> fotos, LocalDateTime timestamp, IncidentReport.Status status) {
        this.id = id;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.fotos = fotos;
        this.timestamp = timestamp;
        this.status = status;
    }
}
