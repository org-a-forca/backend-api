package br.com.aforca.admin.api.controller;

import br.com.aforca.admin.api.exception.NomeJaRegistradoException;
import br.com.aforca.admin.api.model.NovoServicoDto;
import br.com.aforca.admin.api.model.ServicoAsElementDto;
import br.com.aforca.admin.api.model.ServicoDto;
import br.com.aforca.admin.domain.service.ServicoService;
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
@RequestMapping("/servico")
@AllArgsConstructor
public class ServicoController {
  private final ServicoService servicoService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid @NotNull NovoServicoDto novoServicoDto) {
    try {
      return new ResponseEntity<>(servicoService.create(novoServicoDto), HttpStatus.CREATED);
    } catch (NomeJaRegistradoException ex) {
      Map<String, Object> corpoDaResposta = new HashMap<>();
      corpoDaResposta.put("nome", ex.getMessage());

      return new ResponseEntity<>(corpoDaResposta, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ServicoDto getById(@PathVariable @NotNull @Positive Long id) {
    return servicoService.getById(id);
  }

  @GetMapping
  public ResponseEntity<Object> getAll(@RequestParam(required = false) String nome, @RequestParam(required = false, defaultValue = "0") Integer pagNum, @RequestParam(required = false, defaultValue = "5") Integer pagTam) {
    List<ServicoAsElementDto> servicos = servicoService.getAll(nome, pagNum, pagTam);
    Map<String, Object> corpoDaResposta = new HashMap<>();

    if (servicos.isEmpty()) {
      return new ResponseEntity<>(servicos, HttpStatus.OK);
    } else {
      Long qtdeTotalServicos = servicoService.getAllQuantidade(nome);

      corpoDaResposta.put("servicos", servicos);
      corpoDaResposta.put("qtdeServicos", qtdeTotalServicos);
      corpoDaResposta.put("pagNum", pagNum);
      corpoDaResposta.put("pagTam", pagTam);

      return ResponseEntity.ok(corpoDaResposta);
    }
  }

  @PutMapping("/{id}")
  public ServicoDto update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull NovoServicoDto novoServicoDto) {
    return servicoService.update(id, novoServicoDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    servicoService.delete(id);
  }
}
