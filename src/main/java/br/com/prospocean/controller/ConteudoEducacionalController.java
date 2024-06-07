package br.com.prospocean.controller;

import br.com.prospocean.dto.request.ConteudoEducacionalRequestDTO;
import br.com.prospocean.dto.response.ConteudoEducacionalResponseDTO;
import br.com.prospocean.service.ConteudoEducacionalService;
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
@RequestMapping("/api/conteudos-educacionais")
public class ConteudoEducacionalController {

    @Autowired
    private ConteudoEducacionalService conteudoEducacionalService;

    @PostMapping
    public ResponseEntity<ConteudoEducacionalResponseDTO> criarConteudoEducacional(@RequestBody @Valid ConteudoEducacionalRequestDTO conteudoEducacionalRequestDTO) {
        ConteudoEducacionalResponseDTO responseDTO = conteudoEducacionalService.criarConteudoEducacional(conteudoEducacionalRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoEducacionalResponseDTO> atualizarConteudoEducacional(@PathVariable UUID id, @RequestBody @Valid ConteudoEducacionalRequestDTO conteudoEducacionalRequestDTO) {
        ConteudoEducacionalResponseDTO responseDTO = conteudoEducacionalService.atualizarConteudoEducacional(id, conteudoEducacionalRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ConteudoEducacionalResponseDTO>> obterConteudoEducacionalPorId(@PathVariable UUID id) {
        ConteudoEducacionalResponseDTO responseDTO = conteudoEducacionalService.obterConteudoEducacionalPorId(id);
        EntityModel<ConteudoEducacionalResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConteudoEducacionalController.class).obterConteudoEducacionalPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConteudoEducacional(@PathVariable UUID id) {
        conteudoEducacionalService.deletarConteudoEducacional(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ConteudoEducacionalResponseDTO>> listarConteudosEducacionais(@RequestParam(defaultValue = "0") int pagina,
                                                                                            @RequestParam(defaultValue = "10") int tamanho) {
        Page<ConteudoEducacionalResponseDTO> conteudosEducacionais = conteudoEducacionalService.listarConteudosEducacionais(pagina, tamanho);
        return ResponseEntity.ok(conteudosEducacionais);
    }
}
