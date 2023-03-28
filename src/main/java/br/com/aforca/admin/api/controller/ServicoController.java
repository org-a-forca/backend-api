package br.com.aforca.admin.api.controller;

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
  @ResponseStatus(code = HttpStatus.CREATED)
  public ServicoDto create(@RequestBody @Valid @NotNull NovoServicoDto novoServicoDto) {
    return servicoService.create(novoServicoDto);
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
      corpoDaResposta.put("mensagem", "Nenhum serviço encontrado");

      return new ResponseEntity<>(corpoDaResposta, HttpStatus.NOT_FOUND);
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
  public ServicoDto update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull ServicoDto servicoDto) {
    return servicoService.update(id, servicoDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    servicoService.delete(id);
  }
}
