package br.com.prospocean.service;

import br.com.prospocean.controller.NotificacaoController;
import br.com.prospocean.dto.request.NotificacaoRequestDTO;
import br.com.prospocean.dto.response.NotificacaoResponseDTO;
import br.com.prospocean.model.*;
import br.com.prospocean.repository.*;
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
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private IncidentReportRepository incidentReportRepository;

    @Autowired
    private ResiduosRepository residuosRepository;

    @Autowired
    private ConteudoEducacionalRepository conteudoEducacionalRepository;

    @Autowired
    private CentroReciclagemRepository centroReciclagemRepository;

    @Autowired
    private EspecieMarinhaRepository especieMarinhaRepository;

    public NotificacaoResponseDTO criarNotificacao(NotificacaoRequestDTO notificacaoRequestDTO) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(notificacaoRequestDTO.getMensagem());
        notificacao.setTimestamp(notificacaoRequestDTO.getTimestamp());
        notificacao.setTipo(notificacaoRequestDTO.getTipo());

        if (notificacaoRequestDTO.getIncidentReportId() != null) {
            IncidentReport incidentReport = incidentReportRepository.findById(notificacaoRequestDTO.getIncidentReportId())
                    .orElseThrow(() -> new RuntimeException("Relatório de incidente não encontrado"));
            notificacao.setIncidentReport(incidentReport);
        }

        if (notificacaoRequestDTO.getResiduosId() != null) {
            Residuos residuos = residuosRepository.findById(notificacaoRequestDTO.getResiduosId())
                    .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));
            notificacao.setResiduos(residuos);
        }

        if (notificacaoRequestDTO.getConteudoEducacionalId() != null) {
            ConteudoEducacional conteudoEducacional = conteudoEducacionalRepository.findById(notificacaoRequestDTO.getConteudoEducacionalId())
                    .orElseThrow(() -> new RuntimeException("Conteúdo educacional não encontrado"));
            notificacao.setConteudoEducacional(conteudoEducacional);
        }

        if (notificacaoRequestDTO.getCentroReciclagemId() != null) {
            CentroReciclagem centroReciclagem = centroReciclagemRepository.findById(notificacaoRequestDTO.getCentroReciclagemId())
                    .orElseThrow(() -> new RuntimeException("Centro de reciclagem não encontrado"));
            notificacao.setCentroReciclagem(centroReciclagem);
        }

        if (notificacaoRequestDTO.getEspecieMarinhaId() != null) {
            EspecieMarinha especieMarinha = especieMarinhaRepository.findById(notificacaoRequestDTO.getEspecieMarinhaId())
                    .orElseThrow(() -> new RuntimeException("Espécie marinha não encontrada"));
            notificacao.setEspecieMarinha(especieMarinha);
        }

        Notificacao salvo = notificacaoRepository.save(notificacao);

        return new NotificacaoResponseDTO(salvo.getId(), salvo.getMensagem(), salvo.getTimestamp(), salvo.getTipo(),
                salvo.getIncidentReport() != null ? salvo.getIncidentReport().getId() : null,
                salvo.getResiduos() != null ? salvo.getResiduos().getId() : null,
                salvo.getConteudoEducacional() != null ? salvo.getConteudoEducacional().getId() : null,
                salvo.getCentroReciclagem() != null ? salvo.getCentroReciclagem().getId() : null,
                salvo.getEspecieMarinha() != null ? salvo.getEspecieMarinha().getId() : null);
    }

    public NotificacaoResponseDTO atualizarNotificacao(UUID id, NotificacaoRequestDTO notificacaoRequestDTO) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        notificacao.setMensagem(notificacaoRequestDTO.getMensagem());
        notificacao.setTimestamp(notificacaoRequestDTO.getTimestamp());
        notificacao.setTipo(notificacaoRequestDTO.getTipo());

        if (notificacaoRequestDTO.getIncidentReportId() != null) {
            IncidentReport incidentReport = incidentReportRepository.findById(notificacaoRequestDTO.getIncidentReportId())
                    .orElseThrow(() -> new RuntimeException("Relatório de incidente não encontrado"));
            notificacao.setIncidentReport(incidentReport);
        }

        if (notificacaoRequestDTO.getResiduosId() != null) {
            Residuos residuos = residuosRepository.findById(notificacaoRequestDTO.getResiduosId())
                    .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));
            notificacao.setResiduos(residuos);
        }

        if (notificacaoRequestDTO.getConteudoEducacionalId() != null) {
            ConteudoEducacional conteudoEducacional = conteudoEducacionalRepository.findById(notificacaoRequestDTO.getConteudoEducacionalId())
                    .orElseThrow(() -> new RuntimeException("Conteúdo educacional não encontrado"));
            notificacao.setConteudoEducacional(conteudoEducacional);
        }

        if (notificacaoRequestDTO.getCentroReciclagemId() != null) {
            CentroReciclagem centroReciclagem = centroReciclagemRepository.findById(notificacaoRequestDTO.getCentroReciclagemId())
                    .orElseThrow(() -> new RuntimeException("Centro de reciclagem não encontrado"));
            notificacao.setCentroReciclagem(centroReciclagem);
        }

        if (notificacaoRequestDTO.getEspecieMarinhaId() != null) {
            EspecieMarinha especieMarinha = especieMarinhaRepository.findById(notificacaoRequestDTO.getEspecieMarinhaId())
                    .orElseThrow(() -> new RuntimeException("Espécie marinha não encontrada"));
            notificacao.setEspecieMarinha(especieMarinha);
        }

        Notificacao salvo = notificacaoRepository.save(notificacao);

        return new NotificacaoResponseDTO(salvo.getId(), salvo.getMensagem(), salvo.getTimestamp(), salvo.getTipo(),
                salvo.getIncidentReport() != null ? salvo.getIncidentReport().getId() : null,
                salvo.getResiduos() != null ? salvo.getResiduos().getId() : null,
                salvo.getConteudoEducacional() != null ? salvo.getConteudoEducacional().getId() : null,
                salvo.getCentroReciclagem() != null ? salvo.getCentroReciclagem().getId() : null,
                salvo.getEspecieMarinha() != null ? salvo.getEspecieMarinha().getId() : null);
    }

    public NotificacaoResponseDTO obterNotificacaoPorId(UUID id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        return new NotificacaoResponseDTO(notificacao.getId(), notificacao.getMensagem(), notificacao.getTimestamp(), notificacao.getTipo(),
                notificacao.getIncidentReport() != null ? notificacao.getIncidentReport().getId() : null,
                notificacao.getResiduos() != null ? notificacao.getResiduos().getId() : null,
                notificacao.getConteudoEducacional() != null ? notificacao.getConteudoEducacional().getId() : null,
                notificacao.getCentroReciclagem() != null ? notificacao.getCentroReciclagem().getId() : null,
                notificacao.getEspecieMarinha() != null ? notificacao.getEspecieMarinha().getId() : null);
    }

    public void deletarNotificacao(UUID id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notificacaoRepository.delete(notificacao);
    }

    public Page<NotificacaoResponseDTO> listarNotificacoes(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Notificacao> notificacoesPage = notificacaoRepository.findAll(pageable);
        return notificacoesPage.map(notificacao -> new NotificacaoResponseDTO(notificacao.getId(), notificacao.getMensagem(), notificacao.getTimestamp(), notificacao.getTipo(),
                notificacao.getIncidentReport() != null ? notificacao.getIncidentReport().getId() : null,
                notificacao.getResiduos() != null ? notificacao.getResiduos().getId() : null,
                notificacao.getConteudoEducacional() != null ? notificacao.getConteudoEducacional().getId() : null,
                notificacao.getCentroReciclagem() != null ? notificacao.getCentroReciclagem().getId() : null,
                notificacao.getEspecieMarinha() != null ? notificacao.getEspecieMarinha().getId() : null));
    }

    public EntityModel<NotificacaoResponseDTO> adicionarLinksHATEOAS(UUID id, NotificacaoResponseDTO responseDTO) {
        EntityModel<NotificacaoResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NotificacaoController.class).obterNotificacaoPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
