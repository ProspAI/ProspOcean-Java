package br.com.prospocean.dto.request;

import br.com.prospocean.model.IncidentReport;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class IncidentReportRequestDTO {

    @NotNull
    private String descricao;

    @NotNull
    private String localizacao;

    private List<String> fotos;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private IncidentReport.Status status;
}
