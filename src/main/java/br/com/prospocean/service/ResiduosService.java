package br.com.prospocean.service;

import br.com.prospocean.controller.ResiduosController;
import br.com.prospocean.dto.request.ResiduosRequestDTO;
import br.com.prospocean.dto.response.ResiduosResponseDTO;
import br.com.prospocean.model.Residuos;
import br.com.prospocean.repository.ResiduosRepository;
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
public class ResiduosService {

    @Autowired
    private ResiduosRepository residuosRepository;

    public ResiduosResponseDTO criarResiduos(ResiduosRequestDTO residuosRequestDTO) {
        Residuos residuos = new Residuos();
        residuos.setTipo(residuosRequestDTO.getTipo());
        residuos.setLocalizacao(residuosRequestDTO.getLocalizacao());
        residuos.setStatus(residuosRequestDTO.getStatus());
        residuos.setTimestamp(residuosRequestDTO.getTimestamp());

        Residuos salvo = residuosRepository.save(residuos);

        return new ResiduosResponseDTO(salvo.getId(), salvo.getTipo(), salvo.getLocalizacao(), salvo.getStatus(), salvo.getTimestamp());
    }

    public ResiduosResponseDTO atualizarResiduos(UUID id, ResiduosRequestDTO residuosRequestDTO) {
        Residuos residuos = residuosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));

        residuos.setTipo(residuosRequestDTO.getTipo());
        residuos.setLocalizacao(residuosRequestDTO.getLocalizacao());
        residuos.setStatus(residuosRequestDTO.getStatus());
        residuos.setTimestamp(residuosRequestDTO.getTimestamp());

        Residuos salvo = residuosRepository.save(residuos);

        return new ResiduosResponseDTO(salvo.getId(), salvo.getTipo(), salvo.getLocalizacao(), salvo.getStatus(), salvo.getTimestamp());
    }

    public ResiduosResponseDTO obterResiduosPorId(UUID id) {
        Residuos residuos = residuosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));

        return new ResiduosResponseDTO(residuos.getId(), residuos.getTipo(), residuos.getLocalizacao(), residuos.getStatus(), residuos.getTimestamp());
    }

    public void deletarResiduos(UUID id) {
        Residuos residuos = residuosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));
        residuosRepository.delete(residuos);
    }

    public Page<ResiduosResponseDTO> listarResiduos(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Residuos> residuosPage = residuosRepository.findAll(pageable);
        return residuosPage.map(residuos -> new ResiduosResponseDTO(residuos.getId(), residuos.getTipo(), residuos.getLocalizacao(), residuos.getStatus(), residuos.getTimestamp()));
    }

    public EntityModel<ResiduosResponseDTO> adicionarLinksHATEOAS(UUID id, ResiduosResponseDTO responseDTO) {
        EntityModel<ResiduosResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ResiduosController.class).obterResiduosPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
