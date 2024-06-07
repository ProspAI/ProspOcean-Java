package br.com.prospocean.dto.response;

import br.com.prospocean.model.Residuos;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResiduosResponseDTO {

    private UUID id;
    private String tipo;
    private String localizacao;
    private Residuos.Status status;
    private LocalDateTime timestamp;

    public ResiduosResponseDTO(UUID id, String tipo, String localizacao, Residuos.Status status, LocalDateTime timestamp) {
        this.id = id;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.status = status;
        this.timestamp = timestamp;

    }
}
