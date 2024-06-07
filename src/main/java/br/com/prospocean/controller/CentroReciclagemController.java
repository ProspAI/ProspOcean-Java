package br.com.prospocean.controller;

import br.com.prospocean.dto.request.CentroReciclagemRequestDTO;
import br.com.prospocean.dto.response.CentroReciclagemResponseDTO;
import br.com.prospocean.service.CentroReciclagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/centros-reciclagem")
public class CentroReciclagemController {

    @Autowired
    private CentroReciclagemService centroReciclagemService;

    @PostMapping
    public ResponseEntity<CentroReciclagemResponseDTO> criarCentroReciclagem(@RequestBody @Valid CentroReciclagemRequestDTO centroReciclagemRequestDTO) {
        CentroReciclagemResponseDTO responseDTO = centroReciclagemService.criarCentroReciclagem(centroReciclagemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroReciclagemResponseDTO> atualizarCentroReciclagem(@PathVariable UUID id, @RequestBody @Valid CentroReciclagemRequestDTO centroReciclagemRequestDTO) {
        CentroReciclagemResponseDTO responseDTO = centroReciclagemService.atualizarCentroReciclagem(id, centroReciclagemRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CentroReciclagemResponseDTO>> obterCentroReciclagemPorId(@PathVariable UUID id) {
        CentroReciclagemResponseDTO responseDTO = centroReciclagemService.obterCentroReciclagemPorId(id);
        EntityModel<CentroReciclagemResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CentroReciclagemController.class).obterCentroReciclagemPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCentroReciclagem(@PathVariable UUID id) {
        centroReciclagemService.deletarCentroReciclagem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CentroReciclagemResponseDTO>> listarCentrosReciclagem(@RequestParam(defaultValue = "0") int pagina,
                                                                                     @RequestParam(defaultValue = "10") int tamanho) {
        Page<CentroReciclagemResponseDTO> centrosReciclagem = centroReciclagemService.listarCentrosReciclagem(pagina, tamanho);
        return ResponseEntity.ok(centrosReciclagem);
    }
}
