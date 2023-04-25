package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.exception.ServicoNaoVinculadorAoTrabalhadorException;
import br.com.aforca.admin.api.model.ContratoDto;
import br.com.aforca.admin.api.model.ContratoResumoDto;
import br.com.aforca.admin.api.model.NovoContratoDto;
import br.com.aforca.admin.domain.service.ContratoService;
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
@RequestMapping("/contrato")
@AllArgsConstructor
public class ContratoController {
  private final ContratoService contratoService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovoContratoDto novoContratoDto) {
    try {
      return new ResponseEntity<>(contratoService.create(novoContratoDto), HttpStatus.CREATED);
    } catch (ServicoNaoVinculadorAoTrabalhadorException e) {
      Map<String, Object> corpoDaResposta = new HashMap<>();
      corpoDaResposta.put("servicosContratadosIds", e.getMessage());

      return new ResponseEntity<>(corpoDaResposta, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ContratoDto getById(@PathVariable @NotNull @Positive Long id) {
    return contratoService.getById(id);
  }

  @GetMapping
  public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "0") Integer pagNum, @RequestParam(required = false, defaultValue = "5") Integer pagTam) {
    List<ContratoResumoDto> contratos = contratoService.getAll(pagNum, pagTam);
    Map<String, Object> corpoDaResposta = new HashMap<>();

    if (contratos.isEmpty()) {
      return new ResponseEntity<>(contratos, HttpStatus.OK);
    } else {
      var qtdeTotalContratos = contratoService.getAllQuantidade();

      corpoDaResposta.put("contratos", contratos);
      corpoDaResposta.put("qtdeContratos", qtdeTotalContratos);
      corpoDaResposta.put("pagNum", pagNum);
      corpoDaResposta.put("pagTam", pagTam);

      return ResponseEntity.ok(corpoDaResposta);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull NovoContratoDto novoContratoDto) {
    try {
      return ResponseEntity.ok().body(contratoService.update(id, novoContratoDto));
    } catch (ServicoNaoVinculadorAoTrabalhadorException e) {
      Map<String, Object> corpoDaResposta = new HashMap<>();
      corpoDaResposta.put("servicosContratadosIds", e.getMessage());

      return new ResponseEntity<>(corpoDaResposta, HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    contratoService.delete(id);
  }
}
