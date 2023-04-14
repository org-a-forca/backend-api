package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.model.NovoTrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorDto;
import br.com.aforca.admin.api.model.TrabalhadorResumoDto;
import br.com.aforca.admin.domain.service.TrabalhadorService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trabalhador")
@AllArgsConstructor
public class TrabalhadorController {
  private final TrabalhadorService trabalhadorService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovoTrabalhadorDto novoTrabalhadorDto) {
    return new ResponseEntity<>(trabalhadorService.create(novoTrabalhadorDto), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public TrabalhadorDto getById(@PathVariable @NotNull @Positive Long id) {
    return trabalhadorService.getById(id);
  }

  @GetMapping
  public ResponseEntity<Object> getAll(@RequestParam(required = false) String nome, @RequestParam(required = false, defaultValue = "0") Integer pagNum, @RequestParam(required = false, defaultValue = "5") Integer pagTam) {
    List<TrabalhadorResumoDto> trabalhadores = trabalhadorService.getAll(nome, pagNum, pagTam);
    Map<String, Object> corpoDaResposta = new HashMap<>();

    if (trabalhadores.isEmpty()) {
      return new ResponseEntity<>(trabalhadores, HttpStatus.OK);
    } else {
      Long qtdeTotalTrabalhadores = trabalhadorService.getAllQuantidade(nome);

      corpoDaResposta.put("trabalhadores", trabalhadores);
      corpoDaResposta.put("qtdeTrabalhadores", qtdeTotalTrabalhadores);
      corpoDaResposta.put("pagNum", pagNum);
      corpoDaResposta.put("pagTam", pagTam);

      return ResponseEntity.ok(corpoDaResposta);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull NovoTrabalhadorDto novoTrabalhadorDto) {
    return ResponseEntity.ok().body(trabalhadorService.update(id, novoTrabalhadorDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable @NotNull @Positive Long id) {
    try {
      trabalhadorService.delete(id);

      return ResponseEntity.noContent().build();
    } catch (DataIntegrityViolationException ex) {
      Map<String, Object> corpoDaResposta = new HashMap<>();
      corpoDaResposta.put("mensagem", "Esse(a) trabalhador(a) está sendo utilizado(a) em outros registros");

      return new ResponseEntity<>(corpoDaResposta, HttpStatus.CONFLICT);
    }
  }
}
