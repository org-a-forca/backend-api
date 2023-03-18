package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.dtos.ServicoDto;
import br.com.aforca.aforcaadminapi.services.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/servico")
@AllArgsConstructor
public class ServicoController {
  private final ServicoService servicoService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public ServicoDto create(@RequestBody @Valid @NotNull ServicoDto servicoDto) {
    return servicoService.create(servicoDto);
  }

  @GetMapping("/{id}")
  public ServicoDto getById(@PathVariable @NotNull @Positive Long id) {
    return servicoService.getById(id);
  }

  @GetMapping
  public @ResponseBody List<ServicoDto> getAll() {
    return servicoService.getAll();
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

  @GetMapping("/busca")
  public @ResponseBody List<ServicoDto> getAllByNome(@RequestParam(required = true) @NotNull String nome) {
    return servicoService.getAllByNome(nome);
  }
}
