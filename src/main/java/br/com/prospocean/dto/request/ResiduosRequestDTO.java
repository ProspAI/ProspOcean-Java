package br.com.prospocean.dto.request;

import br.com.prospocean.model.Residuos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResiduosRequestDTO {

    @NotNull
    private String tipo;

    @NotNull
    private String localizacao;

    @NotNull
    private Residuos.Status status;

    @NotNull
    private LocalDateTime timestamp;
}
