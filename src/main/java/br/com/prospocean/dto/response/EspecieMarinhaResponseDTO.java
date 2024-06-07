package br.com.prospocean.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class EspecieMarinhaResponseDTO {

    private UUID id;
    private String nomeCientifico;
    private String nomeComum;
    private String habitat;
    private String statusConservacao;

    public EspecieMarinhaResponseDTO(UUID id, String nomeCientifico, String nomeComum, String habitat, String statusConservacao) {
        this.id = id;
        this.nomeCientifico = nomeCientifico;
        this.nomeComum = nomeComum;
        this.habitat = habitat;
        this.statusConservacao = statusConservacao;

    }
}
