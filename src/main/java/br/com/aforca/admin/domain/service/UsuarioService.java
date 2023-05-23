package br.com.aforca.admin.domain.service;

import br.com.aforca.admin.api.exception.EmailJaRegistradoException;
import br.com.aforca.admin.api.model.NovoUsuarioDto;
import br.com.aforca.admin.domain.entity.Usuario;
import br.com.aforca.admin.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder encoder;

  public void registerUser(@Valid @NotNull NovoUsuarioDto novoUsuarioDto) {
    if (usuarioRepository.findByEmail(novoUsuarioDto.getEmail()).isPresent())
      throw new EmailJaRegistradoException("Email do usuário já registrado");

    usuarioRepository.save(new Usuario(novoUsuarioDto.getEmail(), encoder.encode(novoUsuarioDto.getSenha())));
  }
}
