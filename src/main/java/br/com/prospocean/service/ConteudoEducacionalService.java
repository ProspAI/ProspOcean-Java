package br.com.prospocean.service;

import br.com.prospocean.controller.ConteudoEducacionalController;
import br.com.prospocean.dto.request.ConteudoEducacionalRequestDTO;
import br.com.prospocean.dto.response.ConteudoEducacionalResponseDTO;
import br.com.prospocean.model.ConteudoEducacional;
import br.com.prospocean.repository.ConteudoEducacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConteudoEducacionalService {

    @Autowired
    private ConteudoEducacionalRepository conteudoEducacionalRepository;

    public ConteudoEducacionalResponseDTO criarConteudoEducacional(ConteudoEducacionalRequestDTO conteudoEducacionalRequestDTO) {
        ConteudoEducacional conteudoEducacional = new ConteudoEducacional();
        conteudoEducacional.setTitulo(conteudoEducacionalRequestDTO.getTitulo());
        conteudoEducacional.setDescricao(conteudoEducacionalRequestDTO.getDescricao());
        conteudoEducacional.setUrl(conteudoEducacionalRequestDTO.getUrl());

        ConteudoEducacional salvo = conteudoEducacionalRepository.save(conteudoEducacional);

        return new ConteudoEducacionalResponseDTO(salvo.getId(), salvo.getTitulo(), salvo.getDescricao(), salvo.getUrl());
    }

    public ConteudoEducacionalResponseDTO atualizarConteudoEducacional(UUID id, ConteudoEducacionalRequestDTO conteudoEducacionalRequestDTO) {
        ConteudoEducacional conteudoEducacional = conteudoEducacionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo educacional não encontrado"));

        conteudoEducacional.setTitulo(conteudoEducacionalRequestDTO.getTitulo());
        conteudoEducacional.setDescricao(conteudoEducacionalRequestDTO.getDescricao());
        conteudoEducacional.setUrl(conteudoEducacionalRequestDTO.getUrl());

        ConteudoEducacional salvo = conteudoEducacionalRepository.save(conteudoEducacional);

        return new ConteudoEducacionalResponseDTO(salvo.getId(), salvo.getTitulo(), salvo.getDescricao(), salvo.getUrl());
    }

    public ConteudoEducacionalResponseDTO obterConteudoEducacionalPorId(UUID id) {
        ConteudoEducacional conteudoEducacional = conteudoEducacionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo educacional não encontrado"));

        return new ConteudoEducacionalResponseDTO(conteudoEducacional.getId(), conteudoEducacional.getTitulo(), conteudoEducacional.getDescricao(), conteudoEducacional.getUrl());
    }

    public void deletarConteudoEducacional(UUID id) {
        ConteudoEducacional conteudoEducacional = conteudoEducacionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo educacional não encontrado"));
        conteudoEducacionalRepository.delete(conteudoEducacional);
    }

    public Page<ConteudoEducacionalResponseDTO> listarConteudosEducacionais(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<ConteudoEducacional> conteudosEducacionais = conteudoEducacionalRepository.findAll(pageable);
        return conteudosEducacionais.map(conteudoEducacional -> new ConteudoEducacionalResponseDTO(conteudoEducacional.getId(), conteudoEducacional.getTitulo(), conteudoEducacional.getDescricao(), conteudoEducacional.getUrl()));
    }

    public EntityModel<ConteudoEducacionalResponseDTO> adicionarLinksHATEOAS(UUID id, ConteudoEducacionalResponseDTO responseDTO) {
        EntityModel<ConteudoEducacionalResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConteudoEducacionalController.class).obterConteudoEducacionalPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
