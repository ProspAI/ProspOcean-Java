package br.com.prospocean.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UsuarioRequestDTO {

    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;
}
