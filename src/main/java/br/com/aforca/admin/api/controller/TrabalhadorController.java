package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.model.NovoTrabalhadorDto;
import br.com.aforca.admin.domain.service.TrabalhadorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/trabalhador")
@AllArgsConstructor
public class TrabalhadorController {
  private final TrabalhadorService trabalhadorService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovoTrabalhadorDto novoTrabalhadorDto) {
    return new ResponseEntity<>(trabalhadorService.create(novoTrabalhadorDto), HttpStatus.CREATED);
  }
}
