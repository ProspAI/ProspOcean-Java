package br.com.prospocean.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class ConteudoEducacionalResponseDTO {

    private UUID id;
    private String titulo;
    private String descricao;
    private String url;

    public ConteudoEducacionalResponseDTO(UUID id, String titulo, String descricao, String url) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }
}
