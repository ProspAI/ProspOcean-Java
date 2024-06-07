package br.com.prospocean.service;

import br.com.prospocean.controller.EspecieMarinhaController;
import br.com.prospocean.dto.request.EspecieMarinhaRequestDTO;
import br.com.prospocean.dto.response.EspecieMarinhaResponseDTO;
import br.com.prospocean.model.EspecieMarinha;
import br.com.prospocean.repository.EspecieMarinhaRepository;
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
public class EspecieMarinhaService {

    @Autowired
    private EspecieMarinhaRepository especieMarinhaRepository;

    public EspecieMarinhaResponseDTO criarEspecieMarinha(EspecieMarinhaRequestDTO especieMarinhaRequestDTO) {
        EspecieMarinha especieMarinha = new EspecieMarinha();
        especieMarinha.setNomeCientifico(especieMarinhaRequestDTO.getNomeCientifico());
        especieMarinha.setNomeComum(especieMarinhaRequestDTO.getNomeComum());
        especieMarinha.setHabitat(especieMarinhaRequestDTO.getHabitat());
        especieMarinha.setStatusConservacao(especieMarinhaRequestDTO.getStatusConservacao());

        EspecieMarinha salvo = especieMarinhaRepository.save(especieMarinha);

        return new EspecieMarinhaResponseDTO(salvo.getId(), salvo.getNomeCientifico(), salvo.getNomeComum(), salvo.getHabitat(), salvo.getStatusConservacao());
    }

    public EspecieMarinhaResponseDTO atualizarEspecieMarinha(UUID id, EspecieMarinhaRequestDTO especieMarinhaRequestDTO) {
        EspecieMarinha especieMarinha = especieMarinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie marinha não encontrada"));

        especieMarinha.setNomeCientifico(especieMarinhaRequestDTO.getNomeCientifico());
        especieMarinha.setNomeComum(especieMarinhaRequestDTO.getNomeComum());
        especieMarinha.setHabitat(especieMarinhaRequestDTO.getHabitat());
        especieMarinha.setStatusConservacao(especieMarinhaRequestDTO.getStatusConservacao());

        EspecieMarinha salvo = especieMarinhaRepository.save(especieMarinha);

        return new EspecieMarinhaResponseDTO(salvo.getId(), salvo.getNomeCientifico(), salvo.getNomeComum(), salvo.getHabitat(), salvo.getStatusConservacao());
    }

    public EspecieMarinhaResponseDTO obterEspecieMarinhaPorId(UUID id) {
        EspecieMarinha especieMarinha = especieMarinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie marinha não encontrada"));

        return new EspecieMarinhaResponseDTO(especieMarinha.getId(), especieMarinha.getNomeCientifico(), especieMarinha.getNomeComum(), especieMarinha.getHabitat(), especieMarinha.getStatusConservacao());
    }

    public void deletarEspecieMarinha(UUID id) {
        EspecieMarinha especieMarinha = especieMarinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie marinha não encontrada"));
        especieMarinhaRepository.delete(especieMarinha);
    }

    public Page<EspecieMarinhaResponseDTO> listarEspeciesMarinhas(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<EspecieMarinha> especiesMarinhas = especieMarinhaRepository.findAll(pageable);
        return especiesMarinhas.map(especieMarinha -> new EspecieMarinhaResponseDTO(especieMarinha.getId(), especieMarinha.getNomeCientifico(), especieMarinha.getNomeComum(), especieMarinha.getHabitat(), especieMarinha.getStatusConservacao()));
    }

    public EntityModel<EspecieMarinhaResponseDTO> adicionarLinksHATEOAS(UUID id, EspecieMarinhaResponseDTO responseDTO) {
        EntityModel<EspecieMarinhaResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspecieMarinhaController.class).obterEspecieMarinhaPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
