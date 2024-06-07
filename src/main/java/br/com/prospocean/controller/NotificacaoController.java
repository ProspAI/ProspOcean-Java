package br.com.prospocean.controller;

import br.com.prospocean.dto.request.NotificacaoRequestDTO;
import br.com.prospocean.dto.response.NotificacaoResponseDTO;
import br.com.prospocean.service.NotificacaoService;
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
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> criarNotificacao(@RequestBody @Valid NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO responseDTO = notificacaoService.criarNotificacao(notificacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> atualizarNotificacao(@PathVariable UUID id, @RequestBody @Valid NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO responseDTO = notificacaoService.atualizarNotificacao(id, notificacaoRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<NotificacaoResponseDTO>> obterNotificacaoPorId(@PathVariable UUID id) {
        NotificacaoResponseDTO responseDTO = notificacaoService.obterNotificacaoPorId(id);
        EntityModel<NotificacaoResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NotificacaoController.class).obterNotificacaoPorId(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable UUID id) {
        notificacaoService.deletarNotificacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<NotificacaoResponseDTO>> listarNotificacoes(@RequestParam(defaultValue = "0") int pagina,
                                                                           @RequestParam(defaultValue = "10") int tamanho) {
        Page<NotificacaoResponseDTO> notificacoes = notificacaoService.listarNotificacoes(pagina, tamanho);
        return ResponseEntity.ok(notificacoes);
    }
}
