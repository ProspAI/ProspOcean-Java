package br.com.prospocean.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UsuarioResponseDTO {
    private UUID id;
    private String nome;
    private String email;

    public UsuarioResponseDTO(UUID id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
