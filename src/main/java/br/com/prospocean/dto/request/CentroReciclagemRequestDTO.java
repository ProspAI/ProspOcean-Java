package br.com.prospocean.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CentroReciclagemRequestDTO {

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    @NotNull
    private String telefone;

    @NotNull
    @Email
    private String email;
}
