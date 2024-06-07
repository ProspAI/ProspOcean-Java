package br.com.prospocean.controller;

import br.com.prospocean.dto.request.EspecieMarinhaRequestDTO;
import br.com.prospocean.dto.response.EspecieMarinhaResponseDTO;
import br.com.prospocean.service.EspecieMarinhaService;
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
@RequestMapping("/api/especies-marinhas")
public class EspecieMarinhaController {

    @Autowired
    private EspecieMarinhaService especieMarinhaService;

    @PostMapping
    public ResponseEntity<EspecieMarinhaResponseDTO> criarEspecieMarinha(@RequestBody @Valid EspecieMarinhaRequestDTO especieMarinhaRequestDTO) {
        EspecieMarinhaResponseDTO responseDTO = especieMarinhaService.criarEspecieMarinha(especieMarinhaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecieMarinhaResponseDTO> atualizarEspecieMarinha(@PathVariable UUID id, @RequestBody @Valid EspecieMarinhaRequestDTO especieMarinhaRequestDTO) {
        EspecieMarinhaResponseDTO responseDTO = especieMarinhaService.atualizarEspecieMarinha(id, especieMarinhaRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EspecieMarinhaResponseDTO>> obterEspecieMarinhaPorId(@PathVariable UUID id) {
        EspecieMarinhaResponseDTO responseDTO = especieMarinhaService.obterEspecieMarinhaPorId(id);
        EntityModel<EspecieMarinhaResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieMarinhaController.class).obterEspecieMarinhaPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEspecieMarinha(@PathVariable UUID id) {
        especieMarinhaService.deletarEspecieMarinha(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<EspecieMarinhaResponseDTO>> listarEspeciesMarinhas(@RequestParam(defaultValue = "0") int pagina,
                                                                                  @RequestParam(defaultValue = "10") int tamanho) {
        Page<EspecieMarinhaResponseDTO> especiesMarinhas = especieMarinhaService.listarEspeciesMarinhas(pagina, tamanho);
        return ResponseEntity.ok(especiesMarinhas);
    }
}
