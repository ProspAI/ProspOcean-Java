package br.com.prospocean.controller;

import br.com.prospocean.dto.request.UsuarioRequestDTO;
import br.com.prospocean.dto.response.UsuarioResponseDTO;
import br.com.prospocean.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO responseDTO = usuarioService.registrarUsuario(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO responseDTO = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> obterUsuarioPorId(@PathVariable UUID id) {
        UsuarioResponseDTO responseDTO = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok(usuarioService.adicionarLinksHATEOAS(id, responseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuarios(@RequestParam(defaultValue = "0") int pagina,
                                                                   @RequestParam(defaultValue = "10") int tamanho) {
        Page<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios(pagina, tamanho);
        return ResponseEntity.ok(usuarios);
    }
}
