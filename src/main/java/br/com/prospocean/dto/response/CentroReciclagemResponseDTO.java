package br.com.prospocean.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class CentroReciclagemResponseDTO {

    private UUID id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    public CentroReciclagemResponseDTO(UUID id, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;

    }
}
