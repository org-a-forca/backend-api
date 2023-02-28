package br.com.aforca.aforcaadminapi.controllers;

import br.com.aforca.aforcaadminapi.models.Servico;
import br.com.aforca.aforcaadminapi.services.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@AllArgsConstructor
public class ServicoController {
  private final ServicoService servicoService;

  @PostMapping
  public ResponseEntity<Servico> create(@RequestBody Servico servico) {
    servicoService.save(servico);

    return new ResponseEntity<>(servico, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Servico>> getAll() {
    return new ResponseEntity<>(servicoService.getAll(), HttpStatus.OK);
  }
}
