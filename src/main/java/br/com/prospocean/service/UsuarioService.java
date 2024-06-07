package br.com.prospocean.service;

import br.com.prospocean.controller.UsuarioController;
import br.com.prospocean.dto.request.UsuarioRequestDTO;
import br.com.prospocean.dto.response.UsuarioResponseDTO;
import br.com.prospocean.model.Usuario;
import br.com.prospocean.repository.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha()); // Hash a senha em uma implementação real

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha()); // Hash a senha em uma implementação real

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public UsuarioResponseDTO obterUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public void deletarUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public Page<UsuarioResponseDTO> listarUsuarios(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }

    public EntityModel<UsuarioResponseDTO> adicionarLinksHATEOAS(UUID id, UsuarioResponseDTO responseDTO) {
        EntityModel<UsuarioResponseDTO> resource = EntityModel.of(responseDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).obterUsuarioPorId(id)).withSelfRel();
        resource.add(selfLink);
        return resource;
    }
}
