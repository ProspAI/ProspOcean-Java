package br.com.prospocean.controller;

import br.com.prospocean.dto.request.ResiduosRequestDTO;
import br.com.prospocean.dto.response.ResiduosResponseDTO;
import br.com.prospocean.service.ResiduosService;
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
@RequestMapping("/api/residuos")
public class ResiduosController {

    @Autowired
    private ResiduosService residuosService;

    @PostMapping
    public ResponseEntity<ResiduosResponseDTO> criarResiduos(@RequestBody @Valid ResiduosRequestDTO residuosRequestDTO) {
        ResiduosResponseDTO responseDTO = residuosService.criarResiduos(residuosRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResiduosResponseDTO> atualizarResiduos(@PathVariable UUID id, @RequestBody @Valid ResiduosRequestDTO residuosRequestDTO) {
        ResiduosResponseDTO responseDTO = residuosService.atualizarResiduos(id, residuosRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ResiduosResponseDTO>> obterResiduosPorId(@PathVariable UUID id) {
        ResiduosResponseDTO responseDTO = residuosService.obterResiduosPorId(id);
        EntityModel<ResiduosResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ResiduosController.class).obterResiduosPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResiduos(@PathVariable UUID id) {
        residuosService.deletarResiduos(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ResiduosResponseDTO>> listarResiduos(@RequestParam(defaultValue = "0") int pagina,
                                                                    @RequestParam(defaultValue = "10") int tamanho) {
        Page<ResiduosResponseDTO> residuos = residuosService.listarResiduos(pagina, tamanho);
        return ResponseEntity.ok(residuos);
    }
}
