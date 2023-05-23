package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.payload.request.LoginRequest;
import br.com.aforca.admin.api.payload.request.SignupRequest;
import br.com.aforca.admin.api.payload.response.MessageResponse;
import br.com.aforca.admin.api.security.jwt.JwtUtils;
import br.com.aforca.admin.api.security.service.UserDetailsImpl;
import br.com.aforca.admin.domain.entity.Usuario;
import br.com.aforca.admin.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/criar")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (usuarioRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body(new MessageResponse("Erro: Este email já está sendo utilizado!"));
    }

    var usuario = new Usuario(signUpRequest.getEmail(), encoder.encode(signUpRequest.getSenha()));

    usuarioRepository.save(usuario);

    return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
  }

  @PostMapping("/logar")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    var userDetails = (UserDetailsImpl) authentication.getPrincipal();

    var jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new MessageResponse("Usuário logado com sucesso"));
  }

  @PostMapping("/deslogar")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("Você foi deslogado!"));
  }
}
