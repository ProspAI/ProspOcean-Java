package br.com.prospocean.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EspecieMarinhaRequestDTO {

    @NotNull
    private String nomeCientifico;

    @NotNull
    private String nomeComum;

    @NotNull
    private String habitat;

    @NotNull
    private String statusConservacao;
}
