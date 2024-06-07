package br.com.prospocean.service;

import br.com.prospocean.dto.request.CentroReciclagemRequestDTO;
import br.com.prospocean.dto.response.CentroReciclagemResponseDTO;
import br.com.prospocean.model.CentroReciclagem;
import br.com.prospocean.repository.CentroReciclagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CentroReciclagemService {

    @Autowired
    private CentroReciclagemRepository centroReciclagemRepository;

    public CentroReciclagemResponseDTO criarCentroReciclagem(CentroReciclagemRequestDTO centroReciclagemRequestDTO) {
        CentroReciclagem centroReciclagem = new CentroReciclagem();
        centroReciclagem.setNome(centroReciclagemRequestDTO.getNome());
        centroReciclagem.setEndereco(centroReciclagemRequestDTO.getEndereco());
        centroReciclagem.setTelefone(centroReciclagemRequestDTO.getTelefone());
        centroReciclagem.setEmail(centroReciclagemRequestDTO.getEmail());

        CentroReciclagem salvo = centroReciclagemRepository.save(centroReciclagem);

        return new CentroReciclagemResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEndereco(), salvo.getTelefone(), salvo.getEmail());
    }

    public CentroReciclagemResponseDTO atualizarCentroReciclagem(UUID id, CentroReciclagemRequestDTO centroReciclagemRequestDTO) {
        CentroReciclagem centroReciclagem = centroReciclagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de reciclagem não encontrado"));

        centroReciclagem.setNome(centroReciclagemRequestDTO.getNome());
        centroReciclagem.setEndereco(centroReciclagemRequestDTO.getEndereco());
        centroReciclagem.setTelefone(centroReciclagemRequestDTO.getTelefone());
        centroReciclagem.setEmail(centroReciclagemRequestDTO.getEmail());

        CentroReciclagem salvo = centroReciclagemRepository.save(centroReciclagem);

        return new CentroReciclagemResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEndereco(), salvo.getTelefone(), salvo.getEmail());
    }

    public CentroReciclagemResponseDTO obterCentroReciclagemPorId(UUID id) {
        CentroReciclagem centroReciclagem = centroReciclagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de reciclagem não encontrado"));

        return new CentroReciclagemResponseDTO(centroReciclagem.getId(), centroReciclagem.getNome(), centroReciclagem.getEndereco(), centroReciclagem.getTelefone(), centroReciclagem.getEmail());
    }

    public void deletarCentroReciclagem(UUID id) {
        CentroReciclagem centroReciclagem = centroReciclagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de reciclagem não encontrado"));
        centroReciclagemRepository.delete(centroReciclagem);
    }

    public Page<CentroReciclagemResponseDTO> listarCentrosReciclagem(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<CentroReciclagem> centrosReciclagem = centroReciclagemRepository.findAll(pageable);
        return centrosReciclagem.map(centroReciclagem -> new CentroReciclagemResponseDTO(centroReciclagem.getId(), centroReciclagem.getNome(), centroReciclagem.getEndereco(), centroReciclagem.getTelefone(), centroReciclagem.getEmail()));
    }
}
