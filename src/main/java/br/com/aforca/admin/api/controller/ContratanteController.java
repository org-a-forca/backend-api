package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.model.ContratanteResumoDto;
import br.com.aforca.admin.api.model.ContratanteDto;
import br.com.aforca.admin.api.model.NovoContratanteDto;
import br.com.aforca.admin.domain.service.ContratanteService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/contratante")
@AllArgsConstructor
public class ContratanteController {
  private final ContratanteService contratanteService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovoContratanteDto novoContratanteDto) {
    return new ResponseEntity<>(contratanteService.create(novoContratanteDto), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ContratanteDto getById(@PathVariable @NotNull @Positive Long id) {
    return contratanteService.getById(id);
  }

  @GetMapping
  public ResponseEntity<Object> getAll(@RequestParam(required = false) String nome, @RequestParam(required = false) String telefone, @RequestParam(required = false, defaultValue = "0") Integer pagNum, @RequestParam(required = false, defaultValue = "5") Integer pagTam) {
    List<ContratanteResumoDto> contratantes = contratanteService.getAll(nome, telefone, pagNum, pagTam);
    Map<String, Object> corpoDaResposta = new HashMap<>();

    if (contratantes.isEmpty()) {
      return new ResponseEntity<>(contratantes, HttpStatus.OK);
    } else {
      Long qtdeTotalContratantes = contratanteService.getAllQuantidade(nome, telefone);

      corpoDaResposta.put("contratantes", contratantes);
      corpoDaResposta.put("qtdeContratantes", qtdeTotalContratantes);
      corpoDaResposta.put("pagNum", pagNum);
      corpoDaResposta.put("pagTam", pagTam);

      return ResponseEntity.ok(corpoDaResposta);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull NovoContratanteDto novoContratanteDto) {
    return ResponseEntity.ok(contratanteService.update(id, novoContratanteDto));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    contratanteService.delete(id);
  }
}
