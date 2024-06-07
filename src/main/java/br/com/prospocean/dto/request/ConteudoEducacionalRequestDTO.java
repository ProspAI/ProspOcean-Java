package br.com.prospocean.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ConteudoEducacionalRequestDTO {

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @NotNull
    private String url;
}
